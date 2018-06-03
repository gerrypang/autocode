package com.gerry.pang.model;

import java.util.List;

/**
 * 数据源-模型
 * 
 * @author pangguowei
 * @version v1.0.0 2018-06-01
 */
public class DataSourceModel {
	private String dataType;
	private String driverClass;
	private String serverUrl;
	private int port;
	private String databaseName;
	private String username;
	private String password;
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

}