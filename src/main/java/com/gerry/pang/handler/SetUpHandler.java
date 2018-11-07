package com.gerry.pang.handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.gerry.pang.consts.DictCode.AutoCodeProperties;
import com.gerry.pang.consts.DictCode.CommonCode;
import com.gerry.pang.consts.DictCode.GeneralClassType;
import com.gerry.pang.model.CommonModel;
import com.gerry.pang.model.DataSourceModel;
import com.gerry.pang.model.EnumModel;
import com.gerry.pang.model.TableModel;
import com.gerry.pang.utils.AutoCodeClassLoader;
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
	public static Map<String,String> propertyMap = new HashMap<String, String>();
	
	private static final Logger logger = LoggerFactory.getLogger(SetUpHandler.class);
	
	private Map<String,String> getProperties(){
		File temp = new File(CommonCode.CONFIG_PROPERTIES_PATH);
		if (temp.exists()) {
			propertyMap = PropertiesUtils.getAllKeyValue(CommonCode.CONFIG_PROPERTIES_PATH);
		} else {
			String filePath = CommonUtils.getCruuentWorkUrl() + File.separator + "config.properties";
			propertyMap = PropertiesUtils.getAllKeyValue(filePath);
		}
		return propertyMap;
	}
	
	/**
	 * 加载数据源配置项
	 * 
	 * @return DataSourceModel 数据源配置模型
	 */
	public DataSourceModel loadDataSourceConfig() {
		logger.info(">>>>> loadDataSourceConfig() 开始加载数据源配置 ");
		propertyMap = this.getProperties();
		List<TableModel> tableList = new ArrayList<>();
		DataSourceModel dataSource = new DataSourceModel();
		// 使用静态块加载驱动程序
		dataSource.setDatabaseName(propertyMap.get(AutoCodeProperties.JDBC_DATABASE));
		dataSource.setDataType(propertyMap.get(AutoCodeProperties.JDBC_TYPE));
		dataSource.setPassword(propertyMap.get(AutoCodeProperties.JDBC_PASSWORD));
		dataSource.setUsername(propertyMap.get(AutoCodeProperties.JDBC_UERNAME));
		dataSource.setServerUrl(propertyMap.get(AutoCodeProperties.JDBC_URL));
		dataSource.setDriverClass(propertyMap.get(AutoCodeProperties.JDBC_DRIVER));
		if (StringUtils.isNotBlank(propertyMap.get(AutoCodeProperties.JDBC_TABLES))) {
			String[] tables = propertyMap.get(AutoCodeProperties.JDBC_TABLES).split(",");
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
	 * @throws IOException 
	 */
	public CommonModel loadCommonConfig() throws IOException{
		logger.info(">>>>> loadDataSourceConfig() 开始加载通用配置 ");
		propertyMap = this.getProperties();
		CommonModel common = new CommonModel();
		common.setAuthor(propertyMap.get(AutoCodeProperties.GENERAL_AUTHOR));
		common.setPackageName(propertyMap.get(AutoCodeProperties.GENERAL_PACKAGE));
		common.setPath(propertyMap.get(AutoCodeProperties.GENERAL_PATH));
		String[] generals = propertyMap.get(AutoCodeProperties.GENERAL_CLASS).split(",");
		Map<String,String> genMap = new HashMap<String, String>();
		if (generals != null && generals.length > 0) {
			for (String gen : generals) {
				genMap.put(StringUtils.substringBefore(gen, ":"), StringUtils.substringAfterLast(gen, ":"));
			}
		}
		String subDir = common.getPackageName().replace('.', File.separatorChar);
		String parentDir = common.getPath();
		if (".".equals(common.getPath())) {
			parentDir = CommonUtils.getCruuentWorkUrl();
		}
		boolean createSuccess = CommonUtils.creatDirs(parentDir, subDir);
		if (createSuccess) {
			parentDir += File.separator + subDir;
			common.setPath(parentDir);
		} else {
			logger.error("创建包路径出现异常！");
			throw new IOException("创建包路径出现异常！");
		}
		boolean result = false;
		if (genMap.get(GeneralClassType.TYPE_ENTITY) != null) {
			common.setEntityExtendClass(genMap.get(GeneralClassType.TYPE_ENTITY));
			common.setEntity(true);
			if (createSuccess) {
				result = CommonUtils.creatDirs(parentDir, GeneralClassType.TYPE_ENTITY);
				logger.info("{} crate directory {}", GeneralClassType.TYPE_ENTITY, result);
			}
		}
		if (genMap.get(GeneralClassType.TYPE_DTO) != null) {
			common.setDtoExtendClass(genMap.get(GeneralClassType.TYPE_DTO));
			common.setDto(true);
			if (createSuccess) {
				result = CommonUtils.creatDirs(parentDir, GeneralClassType.TYPE_DTO);
				logger.info("{} crate directory {}", GeneralClassType.TYPE_DTO, result);
			}
		}
		if (genMap.get(GeneralClassType.TYPE_ENUM) != null) {
			common.setEnumExtendClass(genMap.get(GeneralClassType.TYPE_ENUM));
			common.setEnum(true);
			if (createSuccess) {
				result = CommonUtils.creatDirs(parentDir, GeneralClassType.TYPE_ENUM + "s");
				logger.info("{} crate directory {}", GeneralClassType.TYPE_ENUM, result);
			}
		}
		if (genMap.get(GeneralClassType.TYPE_ASSEMBLER) != null) {
			common.setAssemblerExtendClass(genMap.get(GeneralClassType.TYPE_ASSEMBLER));
			common.setAssembler(true);
			if (createSuccess) {
				result = CommonUtils.creatDirs(parentDir, GeneralClassType.TYPE_ASSEMBLER);
				logger.info("{} crate directory {}", GeneralClassType.TYPE_ASSEMBLER, result);
			}
		}
		logger.info(">>>>> 完成加载通用配置 ");
		return common;
	}
	
	public List<EnumModel> loadEnumConfig() {
		propertyMap = this.getProperties();
		String enumJson = propertyMap.get(AutoCodeProperties.PERFIX_ENUM);
		List<EnumModel> enumList = new ArrayList<EnumModel>();
		if (StringUtils.isNotBlank(enumJson)) {
			enumList = JSON.parseArray(enumJson, EnumModel.class);
		}
		return enumList;
	}
	
	
	/**
	 * 获取freemarker模板config对象
	 * 
	 * @param common
	 * @return 
	 */
	public Configuration setUpFreemarkerTemplateConfig(CommonModel common) {
		Configuration config = CommonFreemarkerUtils.getFreeMarkerCfg(this.getClass() ,CommonCode.TEMPLATE_PATH);
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
	
	public void loadExtendJar() {
		AutoCodeClassLoader myClassLoader = new AutoCodeClassLoader();
		myClassLoader.selfControlClassLoader(CommonUtils.getCruuentWorkUrl() + File.separator + "lib");
	}
	
}
