package com.gerry.pang.model;

import java.util.List;

/**
 * 数据源-模型
 * 
 * @author gerry_pang
 * @version v1.0.0 2018-06-01
 */
public class DataSourceModel {
	
	/** 数据库类型 */
	private String dataType;
	
	/** 数据库驱动类 */
	private String driverClass;
	
	/** 数据库链接全url */
	private String serverFullUrl;
	
	/** 数据库链接url */
	private String serverUrl;
	
	/** 数据库链接端口 */
	private int port;
	
	/** 数据库名 */
	private String databaseName;

	/** 用户名 */
	private String username;

	/** 密码 */
	private String password;
	
	/** 数据表模型list */
	private List<TableModel> tables;

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<TableModel> getTables() {
		return tables;
	}

	public void setTables(List<TableModel> tables) {
		this.tables = tables;
	}

	public String getServerFullUrl() {
		return serverFullUrl;
	}

	public void setServerFullUrl(String serverFullUrl) {
		this.serverFullUrl = serverFullUrl;
	}

}