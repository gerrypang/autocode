package com.gerry.pang.auto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gerry.pang.consts.DictCode.CommonCode;
import com.gerry.pang.consts.DictCode.DatabaseType;
import com.gerry.pang.consts.DictCode.GeneralClassType;
import com.gerry.pang.handler.AutoCodeHandler;
import com.gerry.pang.handler.impl.MySQLAutoCodeHandlerImpl;
import com.gerry.pang.model.ColumnModel;
import com.gerry.pang.model.CommonModel;
import com.gerry.pang.model.DataSourceModel;
import com.gerry.pang.model.TableModel;
import com.gerry.pang.utils.CommonUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * 代码生成类
 * 
 * @author gerry_pang
 * @version v1.0.0 2018-06-11
 */
public class CodeGenerator {
	
	private static final Logger logger = LoggerFactory.getLogger(CodeGenerator.class);
	
	/**
	 * 根据数据库类型，生成handler类
	 * 
	 * @param datasource
	 * @return
	 */
	private AutoCodeHandler getHandlerByDataBaseType(DataSourceModel datasource) {
		AutoCodeHandler mysqlHandler = null;
		if (datasource.getDataType().equalsIgnoreCase(DatabaseType.MYSQL)) {
			mysqlHandler = new MySQLAutoCodeHandlerImpl();
		}
		return mysqlHandler;
	}
	
	/**
	 * 生成Entity 实体类
	 * 
	 * @param datasource
	 * @param config
	 * @param commonData
	 */
	public void generateEntityClass(DataSourceModel datasource, Configuration config, CommonModel commonData) {
		Map<String, Object> data = new HashMap<>();
		AutoCodeHandler handler = getHandlerByDataBaseType(datasource);
		List<TableModel> tableList = handler.getTableList(datasource);
		for (TableModel tableOne : tableList) {
			List<ColumnModel> cols = handler.getColumnOfTable(datasource, tableOne);
			Set<String> importClass = getImportClass(cols);
			String extendClassName = commonData.getEntityExtendClass();
			if (StringUtils.isNotBlank(extendClassName)) {
				importClass.add(extendClassName);
				cols = excludeSameInExtendColumns(cols, extendClassName);
			}
			tableOne.setImportClass(importClass);
			data.put("table", tableOne);
			data.put("extendClass", extendClassName);
			data.put("columnList", cols);
			try {
				String fullPathName = CommonUtils.jointNewPath(commonData.getPath(), GeneralClassType.TYPE_ENTITY, tableOne.getJavaName());
				FileOutputStream fos = new FileOutputStream(new File(fullPathName));
				Writer out = new BufferedWriter(new OutputStreamWriter(fos, CommonCode.DEFAULT_CHARTSET), 10240);
				Template entityTemplate = config.getTemplate("EntityClass.ftl");
				entityTemplate.process(data, out);
				out.close();
			} catch (TemplateNotFoundException e) {
				logger.error(e.toString());
			} catch (MalformedTemplateNameException e) {
				logger.error(e.toString());
			} catch (ParseException e) {
				logger.error(e.toString());
			} catch (IOException e) {
				logger.error(e.toString());
			} catch (TemplateException e) {
				logger.error(e.toString());
			}
		}
	}
	
	/**
	 * 生产DTO 类
	 * 
	 * @param datasource
	 * @param config
	 * @param commonData
	 */
	public void generateDtoClass(DataSourceModel datasource, Configuration config, CommonModel commonData){
		Map<String, Object> data = new HashMap<>();
		AutoCodeHandler handler = getHandlerByDataBaseType(datasource);
		List<TableModel> tableList = handler.getTableList(datasource);
		for (TableModel tableOne : tableList) {
			List<ColumnModel> cols = handler.getColumnOfTable(datasource, tableOne);
			Set<String> importClass = getImportClass(cols);
			importClass.add(commonData.getEntityExtendClass());
			String extendClassName = commonData.getEntityExtendClass();
			if (StringUtils.isNotBlank(extendClassName)) {
				importClass.add(extendClassName);
				cols = excludeSameInExtendColumns(cols, extendClassName);
			}
			tableOne.setImportClass(importClass);
			data.put("table", tableOne);
			data.put("extendClass", extendClassName);
			data.put("columnList", cols);
			try {
				String dtoFileName = tableOne.getJavaName() + GeneralClassType.TYPE_DTO.toUpperCase();
				String fullPathName = CommonUtils.jointNewPath(commonData.getPath(), GeneralClassType.TYPE_DTO, dtoFileName);
				FileOutputStream fos = new FileOutputStream(new File(fullPathName));
				Writer out = new BufferedWriter(new OutputStreamWriter(fos, CommonCode.DEFAULT_CHARTSET), 10240);
				Template entityTemplate = config.getTemplate("DTOClass.ftl");
				entityTemplate.process(data, out);
				out.close();
			} catch (TemplateNotFoundException e) {
				logger.error(e.toString());
			} catch (MalformedTemplateNameException e) {
				logger.error(e.toString());
			} catch (ParseException e) {
				logger.error(e.toString());
			} catch (IOException e) {
				logger.error(e.toString());
			} catch (TemplateException e) {
				logger.error(e.toString());
			}
		}
	}
	
	public void generateEnumClass(){}
	
	public void generateAssemblerClass(){}
	
	public void generateHbm(){}
	
	/**
	 * 对于继承类，去除重复字段
	 * 
	 * @param originalColumns
	 * @param extendClassName
	 * @return
	 */
	private List<ColumnModel> excludeSameInExtendColumns(List<ColumnModel> originalColumns, String extendClassName){
		List<ColumnModel> noRepeatColumns = new ArrayList<ColumnModel>();
		List<ColumnModel> extendColumns = getExtendClassColunms(extendClassName);
		originalColumns.stream().filter(item -> {
			 boolean flag = !extendColumns.contains(item);
			 if (flag) {
				 noRepeatColumns.add(item);
			 }
			 return flag;
			}).collect(Collectors.toList());
		return noRepeatColumns;
	}
	
	/**
	 * 获取继承类的中的字段
	 * 
	 * @param extendClassName
	 * @return
	 */
	private List<ColumnModel> getExtendClassColunms(String extendClassName) {
		List<ColumnModel> extendsColumns = new ArrayList<ColumnModel>();
		ColumnModel columnOne = null;
		try {
			Class<?> classExtend = Class.forName(extendClassName);
			Field[] extendFields = classExtend.getDeclaredFields();
			for (Field field : extendFields) {
				boolean fieldHasAnno = field.isAnnotationPresent(Column.class);  
				if(fieldHasAnno){
					Column annoColumn = field.getAnnotation(Column.class);  
					if (annoColumn != null) {
						columnOne = new ColumnModel();
						columnOne.setJavaType(field.getType().getSimpleName());
						columnOne.setJavaName(field.getName());
						columnOne.setColumnName(annoColumn.name());
						extendsColumns.add(columnOne);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			logger.error(e.toString());
		}
		return extendsColumns;
	}
	
	/**
	 * 对引入类进行去重
	 * 
	 * @param cols
	 * @return
	 */
	private Set<String> getImportClass(List<ColumnModel> cols){
		Set<String> importClass = cols.stream().filter(
				n -> n != null 
				&& StringUtils.isNotBlank(n.getImportClass()) 
				&& !StringUtils.contains(n.getImportClass(), "java.lang"))
			.map(n -> n.getImportClass())
			.sorted().collect(Collectors.toSet());		
		return importClass;
	}
}
