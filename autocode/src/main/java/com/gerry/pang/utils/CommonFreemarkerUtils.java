package com.gerry.pang.utils;

import java.io.File;
import java.io.IOException;

import com.gerry.pang.consts.DictCode.CommonCode;

import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

/**
 * freemarker生成配置
 * 
 * @author pangguowei
 * @version 2018-06-05 1:51:20
 */
public class CommonFreemarkerUtils {
	
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
			e.printStackTrace();
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
