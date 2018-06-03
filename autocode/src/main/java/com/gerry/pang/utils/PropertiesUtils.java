package com.gerry.pang.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.gerry.pang.consts.DictCode.CommonCode;


public class PropertiesUtils {

    /** 
     * 功能：从userInfo.properties文件中读取出一个key对应的value 
     * @param filename  eg:src/userInfo.properties
     * @param keyName   接收一个key值 
     * 
     * @return 返回value值 
     */  
    public static String getValue(String filename, String keyName){  
        String value = "";  
        Properties p = new Properties();  
        try {  
            //读取jdbc.properties文件,使用InputStreamReader字符流防止文件中出现中文导致乱码  
            p.load(new InputStreamReader(new FileInputStream(filename), CommonCode.DEFAULT_CHARTSET));  
            value = p.getProperty(keyName,"获取失败");  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return value;  
    }  
    
    /** 
     * 功能：从userInfo.properties文件中读取出一个key对应的value 
     * @param filename  eg:src/userInfo.properties
     * @param keyName   接收一个key值 
     * 
     * @return 返回value值 
     */  
    public static Map<String,String> getAllKeyValue(String filename){  
    	Map<String,String> properties = new HashMap<String, String>(16);
        String value = "";  
        Properties p = new Properties();  
        try {  
            //读取jdbc.properties文件,使用InputStreamReader字符流防止文件中出现中文导致乱码  
            p.load(new InputStreamReader(new FileInputStream(filename), CommonCode.DEFAULT_CHARTSET));  
            Set<Object> allSet = p.keySet();
            for(Object key : allSet){ 
            	value = p.getProperty(key.toString(), "");
            	properties.put(key.toString(), value);
            }
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return properties;  
    }

}
