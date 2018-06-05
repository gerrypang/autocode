package com.gerry.pang.auto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gerry.pang.consts.DictCode.CommonCode;
import com.gerry.pang.consts.DictCode.GeneralClassType;
import com.gerry.pang.handler.impl.MySQLAutoCodeHanlerImpl;
import com.gerry.pang.model.ColumnModel;
import com.gerry.pang.model.DataSourceModel;
import com.gerry.pang.model.TableModel;
import com.gerry.pang.utils.CommonUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class CodeGenerator {
	
	private static final Logger logger = LoggerFactory.getLogger(CodeGenerator.class);
	
	public void generateEntityClass(DataSourceModel datasource, Configuration config, String basePath){
		Map<String, Object> data = new HashMap<>();
		MySQLAutoCodeHanlerImpl mysqlHandler = new MySQLAutoCodeHanlerImpl();
		List<TableModel> tableList = mysqlHandler.getTableList(datasource);
		for (TableModel tableOne : tableList) {
			List<ColumnModel> cols = mysqlHandler.getColumnOfTable(datasource, tableOne);
			Set<String> importClass = cols.stream().filter(n -> n != null && StringUtils.isNotBlank(n.getImportClass()))
					.map(n -> n.getImportClass()).sorted().collect(Collectors.toSet());
			tableOne.setImportClass(importClass);
			data.put("table", tableOne);
			data.put("columnList", cols);
			try {
				String fullPathName = CommonUtils.jointNewPath(basePath, GeneralClassType.TYPE_ENTITY, tableOne.getJavaName());
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
	
	public void generateDtoClass(){}
	
	public void generateEnumClass(){}
	
	public void generateAssemblerClass(){}
	
	public void generateHbm(){}
}
