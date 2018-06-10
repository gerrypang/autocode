package com.gerry.pang.utils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoCodeClassLoader {
	
	private static final Logger logger = LoggerFactory.getLogger(AutoCodeClassLoader.class);
	
    public static Method addURL = null;  
    
    public static URLClassLoader classloader = null;  

	public void selfControlClassLoader(String jarPath) {
		logger.debug("jarPath :{}", jarPath);
		addURL = initAddMethod();
		classloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		logger.debug("allExtJars :{}", classloader);
		try {
			List<String> allExtJars = getFiles(jarPath);
			URL classUrl = null;
			StringBuffer urlPath = new StringBuffer(100);
			for (String jarName : allExtJars) {
				urlPath.append("file:").append(jarName);
				logger.debug("load extend jar:{}", urlPath.toString());
				classUrl = new URL(urlPath.toString());  
				urlPath.delete(0, urlPath.length());
				addURL.invoke(classloader, new Object[] { classUrl });
			}
			
		} catch (MalformedURLException e) {
			logger.error(e.toString());
		} catch (IllegalAccessException e) {
			logger.error(e.toString());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
		} catch (InvocationTargetException e) {
			logger.error(e.toString());
		}
	}

	public static Method initAddMethod() {
		try {
			addURL = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
			addURL.setAccessible(true);
			return addURL;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static ArrayList<String> getFiles(String path) {
	    ArrayList<String> files = new ArrayList<String>();
	    File file = new File(path);
	    if (file.isDirectory()) {
	    	File[] tempList = file.listFiles();
	    	for (int i = 0; i < tempList.length; i++) {
	    		if (tempList[i].isFile()) {
	    			logger.debug("jar文件：{}", tempList[i]);
	    			files.add(tempList[i].toString());
	    		}
	    		if (tempList[i].isDirectory()) {
	    			logger.debug("文件夹：{}", tempList[i]);
	    		}
	    	}
	    }
	    return files;
	}
}
