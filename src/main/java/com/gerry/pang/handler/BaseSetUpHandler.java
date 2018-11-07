package com.gerry.pang.handler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gerry.pang.consts.DictCode.AutoCodeProperties;
import com.gerry.pang.consts.DictCode.CommonCode;
import com.gerry.pang.consts.DictCode.GeneralClassType;
import com.gerry.pang.model.CommonModel;
import com.gerry.pang.utils.AutoCodeClassLoader;
import com.gerry.pang.utils.CommonFreemarkerUtils;
import com.gerry.pang.utils.CommonUtils;
import com.gerry.pang.utils.DateUtils;
import com.gerry.pang.utils.PropertiesUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

public class BaseSetUpHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseSetUpHandler.class);
	
	/** 加载自动生成配置参数文件*/
	public static Map<String,String> propertyMap = new HashMap<String, String>();

	protected Map<String,String> getProperties(){
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
		common.setType(propertyMap.get(AutoCodeProperties.GENERAL_TYPE));
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
		common.setBasePath(parentDir);
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
				logger.info("{} 创建包路径 {}", GeneralClassType.TYPE_ENTITY, result);
			}
		}
		if (genMap.get(GeneralClassType.TYPE_DTO) != null) {
			common.setDtoExtendClass(genMap.get(GeneralClassType.TYPE_DTO));
			common.setDto(true);
			if (createSuccess) {
				result = CommonUtils.creatDirs(parentDir, GeneralClassType.TYPE_DTO);
				logger.info("{} 创建包路径 {}", GeneralClassType.TYPE_DTO, result);
			}
		}
		if (genMap.get(GeneralClassType.TYPE_ENUM) != null) {
			common.setEnumExtendClass(genMap.get(GeneralClassType.TYPE_ENUM));
			common.setEnum(true);
			if (createSuccess) {
				result = CommonUtils.creatDirs(parentDir, GeneralClassType.TYPE_ENUM + "s");
				logger.info("{} 创建包路径  {}", GeneralClassType.TYPE_ENUM, result);
			}
		}
		if (genMap.get(GeneralClassType.TYPE_ASSEMBLER) != null) {
			common.setAssemblerExtendClass(genMap.get(GeneralClassType.TYPE_ASSEMBLER));
			common.setAssembler(true);
			if (createSuccess) {
				result = CommonUtils.creatDirs(parentDir, GeneralClassType.TYPE_ASSEMBLER);
				logger.info("{} 创建包路径  {}", GeneralClassType.TYPE_ASSEMBLER, result);
			}
		}
		if (genMap.get(GeneralClassType.TYPE_MAPPER) != null) {
			common.setMapper(true);
			result = CommonUtils.creatDirs(parentDir, GeneralClassType.TYPE_MAPPER);
			logger.info("{} 创建包路径  {}", GeneralClassType.TYPE_MAPPER, result);
		}
		
		logger.info(">>>>> 完成加载通用配置 ");
		return common;
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
