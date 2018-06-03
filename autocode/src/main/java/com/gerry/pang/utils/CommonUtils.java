package com.gerry.pang.utils;

import java.io.File;

import com.gerry.pang.consts.DictCode.DatabaseDriverClass;
import com.gerry.pang.consts.DictCode.DatabaseType;
import com.gerry.pang.consts.DictCode.DatabaseURL;
import com.gerry.pang.model.DataSourceModel;

public class CommonUtils {

	/**
	 * 根据数据库类型获取driverClass
	 * 
	 * @param type
	 * @return 
	 * @author pangguowei
	 * @version 2018-06-01-1:57:44
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
	 * @author pangguowei
	 * @version 2018-06-01-1:57:44
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
	 * @author pangguowei
	 * @version 2018-06-01-1:57:44
	 */
	public static String getJarFileUrl(){
		String path = System.getProperty("java.class.path");
		int firstIndex = path.lastIndexOf(System.getProperty("path.separator")) + 1;
		int lastIndex = path.lastIndexOf(File.separator) + 1;
		path = path.substring(firstIndex, lastIndex);
		return path;
	}
	
//	public static void main(String[] args) {
//		String aa = getJarFileUrl();
//		System.out.println();
//	}
	
}
