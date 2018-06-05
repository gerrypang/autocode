package com.gerry.pang.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gerry.pang.consts.DictCode.AutoCodeProperties;
import com.gerry.pang.consts.DictCode.CommonCode;
import com.gerry.pang.consts.DictCode.GeneralClassType;
import com.gerry.pang.model.CommonModel;
import com.gerry.pang.model.DataSourceModel;
import com.gerry.pang.model.TableModel;
import com.gerry.pang.utils.CommonFreemarkerUtils;
import com.gerry.pang.utils.CommonUtils;
import com.gerry.pang.utils.DateUtils;
import com.gerry.pang.utils.PropertiesUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

/**
 * 代码自动生成准备类
 * 
 * @author gerry_pang
 * @version v1.0.0 2018-06-04
 */
public class SetUpHandler {
	
	/** 加载自动生成配置参数文件*/
	public static final Map<String,String> propertyMap = PropertiesUtils.getAllKeyValue(CommonCode.CONFIG_PROPERTIES_PATH);
	
	private static final Logger logger = LoggerFactory.getLogger(SetUpHandler.class);
	
	/**
	 * 加载数据源配置项
	 * 
	 * @return DataSourceModel 数据源配置模型
	 */
	public DataSourceModel loadDataSourceConfig() {
		logger.info(">>>>> loadDataSourceConfig() 开始加载数据源配置 ");
		List<TableModel> tableList = new ArrayList<>();
		DataSourceModel dataSource = new DataSourceModel();
		// 使用静态块加载驱动程序
		dataSource.setDatabaseName(propertyMap.get(AutoCodeProperties.JDBC_DATABASE));
		dataSource.setDataType(propertyMap.get(AutoCodeProperties.JDBC_TYPE));
		dataSource.setPassword(propertyMap.get(AutoCodeProperties.JDBC_PASSWORD));
		dataSource.setUsername(propertyMap.get(AutoCodeProperties.JDBC_UERNAME));
		dataSource.setServerUrl(propertyMap.get(AutoCodeProperties.JDBC_URL));
		dataSource.setDriverClass(propertyMap.get(AutoCodeProperties.JDBC_DRIVER));
		if (StringUtils.isNotBlank(propertyMap.get(AutoCodeProperties.GENERAL_TABLES))) {
			String[] tables = propertyMap.get(AutoCodeProperties.GENERAL_TABLES).split(",");
			TableModel tableOne = null;
			if (tables != null && tables.length > 0) {
				for (String tableName : tables) {
					tableOne = new TableModel();
					tableOne.setTableName(tableName);
					tableList.add(tableOne);
				}
				dataSource.setTables(tableList);
			}
		}
		logger.info(">>>>> 完成加载数据源配置 ");
		return dataSource;
	}
	
	/**
	 * 加载通用配置项
	 * 
	 * @return CommonModel 通用配置模型
	 */
	public CommonModel loadCommonConfig(){
		logger.info(">>>>> loadDataSourceConfig() 开始加载通用配置 ");
		CommonModel common = new CommonModel();
		common.setAuthor(propertyMap.get(AutoCodeProperties.GENERAL_AUTHOR));
		common.setPackageName(propertyMap.get(AutoCodeProperties.GENERAL_PACKAGE));
		String[] generals = propertyMap.get(AutoCodeProperties.GENERAL_CLASS).split(",");
		String subDir = common.getPackageName().replace('.', '/');
		String parentDir = common.getPath();
		if (propertyMap.get(AutoCodeProperties.GENERAL_PATH).equals(common.getPath())) {
			parentDir = CommonUtils.getCruuentWorkUrl();
		}
		boolean createSuccess = CommonUtils.creatDirs(parentDir, subDir);
		common.setPath(parentDir);
		if (StringUtils.isNotBlank(propertyMap.get(AutoCodeProperties.GENERAL_CLASS))) {
			if (generals != null && generals.length > 0) {
				for (String gen : generals) {
					if (GeneralClassType.TYPE_ENTITY.equalsIgnoreCase(gen)) {
						common.setAssembler(true);
						if (createSuccess) {
							CommonUtils.creatDirs(parentDir, GeneralClassType.TYPE_ENTITY);
						}
					}
					if (GeneralClassType.TYPE_DTO.equalsIgnoreCase(gen)) {
						common.setDto(true);
						if (createSuccess) {
							CommonUtils.creatDirs(parentDir, GeneralClassType.TYPE_DTO);
						}
					}
					if (GeneralClassType.TYPE_ENUM.equalsIgnoreCase(gen)) {
						common.setEnum(true);
						if (createSuccess) {
							CommonUtils.creatDirs(parentDir, GeneralClassType.TYPE_ENUM);
						}
					}
					if (GeneralClassType.TYPE_Assembler.equalsIgnoreCase(gen)) {
						common.setAssembler(true);
						if (createSuccess) {
							CommonUtils.creatDirs(parentDir, GeneralClassType.TYPE_Assembler);
						}
					}
				}
			}
		}
		logger.info(">>>>> 完成加载通用配置 ");
		return common;
	}
	
	public static void main(String[] args) {
		new SetUpHandler().loadCommonConfig();
	}
	
	/**
	 * 获取freemarker模板config对象
	 * 
	 * @param common
	 * @return 
	 * @author  pangguowei
	 * @version 2018-06-05 2:03:11
	 */
	public Configuration setUpFreemarkerTemplateConfig(CommonModel common) {
		Configuration config = CommonFreemarkerUtils.getFreeMarkerCfg(CommonCode.TEMPLATE_PATH);
		try {
			config.setSharedVariable("author", common.getAuthor());
			config.setSharedVariable("packageName", common.getPackageName());
			config.setSharedVariable("generatePath", common.getPath());
			config.setSharedVariable("generateDateTime", DateUtils.getCurrentTime());
		} catch (TemplateModelException e) {
			logger.error(e.toString());
		}
		return config;
	}

}
