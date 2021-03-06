package com.gerry.pang.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.gerry.pang.consts.DictCode.CommonCode;
import com.gerry.pang.consts.DictCode.DatabaseDriverClass;
import com.gerry.pang.consts.DictCode.DatabaseType;
import com.gerry.pang.consts.DictCode.DatabaseURL;
import com.gerry.pang.consts.MySQLDataTypeMapping;
import com.gerry.pang.model.DataSourceModel;

/**
 * 通用工具类
 * 
 * @author gerry_pang
 * @version v1.0.0 2018-06-01
 */
public class CommonUtils {

	/**
	 * 根据数据库类型获取driverClass
	 * 
	 * @param type
	 * @return 
	 */
	public static String getDBDriverClassByType(String type) {
		if (type.equalsIgnoreCase(DatabaseType.MYSQL)) {
			return DatabaseDriverClass.MYSQL_DRIVER_CLASS;
		}
		if (type.equalsIgnoreCase(DatabaseType.ORACLE)) {
			return DatabaseDriverClass.ORACLE_DRIVER_CLASS;
		}
		if (type.equalsIgnoreCase(DatabaseType.DB2)) {
			return DatabaseDriverClass.DB2_DRIVER_CLASS;
		}
		return "";
	}
	
	/**
	 * 根据数据库类型获取url
	 * 
	 * @param type
	 * @return 
	 * @version 2018-06-01 
	 */
	public static String getDBUrlByType(DataSourceModel dataSource) {
		String url = "";
		if (DatabaseType.MYSQL.equalsIgnoreCase(dataSource.getDataType())) {
			url = DatabaseURL.MYSQL_URL;
		}
		if (DatabaseType.ORACLE.equalsIgnoreCase(dataSource.getDataType())) {
			url =  DatabaseDriverClass.ORACLE_DRIVER_CLASS;
		}
		if (DatabaseType.DB2.equalsIgnoreCase(dataSource.getDataType())) {
			url =  DatabaseDriverClass.DB2_DRIVER_CLASS;
		}
		url = url.replaceAll("ip", dataSource.getServerUrl())
				.replaceAll("port", String.valueOf(dataSource.getPort()))
				.replaceAll("dbname", dataSource.getDatabaseName());
		return url;
	}
	
	/**
	 * 获取jar路径
	 * 
	 * @return 
	 */
	public static String getJarFileUrl(){
		String path = System.getProperty("java.class.path");
		int firstIndex = path.lastIndexOf(System.getProperty("user.dir")) + 1;
		int lastIndex = path.lastIndexOf(File.separator) + 1;
		path = path.substring(firstIndex, lastIndex);
		return path;
	}
	
	/**
	 * 获取当前工作路径
	 * 
	 * @return 
	 */
	public static String getCruuentWorkUrl(){
		String path = System.getProperty("user.dir");
		return path;
	}
	
	/**
	 * 生成默认Java类型
	 * 
	 * @param basePath
	 * @param type
	 * @param fileName
	 * @return
	 */
	public static String jointNewPath(String basePath, String type, String fileName) {
		return jointNewPath(basePath, type, fileName, CommonCode.SUFFIX);
	}

	/**
	 * 生成文件路径
	 * 
	 * @param basePath
	 * @param fileName
	 * @return
	 */
	public static String jointNewPath(String basePath, String fileName) {
		StringBuilder filePath = new StringBuilder(100);
		filePath.append(basePath).append(File.separatorChar).append(fileName);
		return filePath.toString();
	}
	
	/**
	 * 生成文件路径
	 * 
	 * @param basePath
	 * @param type
	 * @param fileName
	 * @param suffix
	 * @return
	 */
	public static String jointNewPath(String basePath, String type, String fileName, String suffix) {
		StringBuilder filePath = new StringBuilder(100);
		filePath.append(basePath);
		if(StringUtils.isNotBlank(type)){
			filePath.append(File.separatorChar).append(type);
		}
		filePath.append(File.separatorChar).append(fileName);
		if(StringUtils.isNotBlank(suffix)){
			filePath.append(suffix);
		}
		return filePath.toString();
	}
	
	
	/**
	 * 创建文件夹
	 * 
	 * @return 
	 */
	public static boolean creatDirs(String aParentDir, String aSubDir) {
		File aFile = new File(aParentDir);
		if (aFile.exists()) {
			File aSubFile = new File(aParentDir + File.separator + aSubDir);
			if (!aSubFile.exists()) {
				return aSubFile.mkdirs();
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 获取数据类型
	 * 
	 * @return 
	 */
	public static String getDataType(String args) {
		if (StringUtils.isNotBlank(args)) {
			if (StringUtils.contains(args, "(")) {
				return StringUtils.substringBefore(args, "(");
			}
		}
		return args;
	}

	/**
	 * 获取数据长度
	 * 
	 * @return 
	 */
	public static List<String> getDataLength(String args) {
		String temp = "";
		List<String> dataLength = new ArrayList<String>();
		if (StringUtils.isNotBlank(args)) {
			if (StringUtils.contains(args, "(")) {
				temp = StringUtils.substringBetween(args, "(", ")");
				if (StringUtils.contains(temp, ",")) {
					dataLength = Arrays.asList(temp.split(","));
				} else {
					dataLength.add(temp);
				}
			}
		}
		return dataLength;
	}
	
	public static String getIdGenerateStrategy(String databaseType, String colunmType, int colunmSize, String extra) {
		String strategy = "";
		if (DatabaseType.MYSQL.equalsIgnoreCase(databaseType)) {
			if (StringUtils.isNotBlank(extra)) {
				strategy = MySQLDataTypeMapping.generateKeyStategyMapping.get(extra);
			} else {
				strategy = MySQLDataTypeMapping.generateKeyStategyMapping.get(colunmType);
				if (colunmType.equalsIgnoreCase("varchar") || colunmType.equalsIgnoreCase("char")) {
					strategy = colunmSize >= 32 ? "uuid" : "assigned";
				}
			}
		}
		return strategy;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(getJarFileUrl());
	}
	
}
