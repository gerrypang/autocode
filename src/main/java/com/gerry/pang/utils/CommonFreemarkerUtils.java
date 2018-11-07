package com.gerry.pang.utils;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gerry.pang.consts.DictCode.CommonCode;
import com.gerry.pang.handler.DataBaseSetUpHandler;

import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

/**
 * freemarker生成配置
 * 
 * @author gerry_pang
 * @version 2018-06-05
 */
public class CommonFreemarkerUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(DataBaseSetUpHandler.class);
	
	private CommonFreemarkerUtils() {
		
	}
	
    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_22);
    
    static {
    	CONFIGURATION.setBooleanFormat("true,false");
    	CONFIGURATION.setNumberFormat("#");
    	CONFIGURATION.setDefaultEncoding(CommonCode.DEFAULT_CHARTSET);
    	CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    	CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
    }
	
	public static Configuration getFreeMarkerCfg(String ftlPath) {
		try {
			CONFIGURATION.setDirectoryForTemplateLoading(new File(ftlPath));
		} catch (IOException e) {
			logger.error("加载模板路径出现异常，{}", e.toString());
		}
		return CONFIGURATION;
	}
	
	public static Configuration getFreeMarkerCfg(Class<?> cls, String templatePath) {
		CONFIGURATION.setClassForTemplateLoading(cls, templatePath);
		return CONFIGURATION;
	}
	
    public static void clearCache() {
        CONFIGURATION.clearTemplateCache();
    }
}
