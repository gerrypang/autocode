package com.gerry.pang.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 数据表-模型
 * 
 * @author gerry_pang
 * @version v1.0.0 2018-06-01
 */
public class TableModel{
	
	/** 数据表名*/
	private String tableName;
	
	/** 映射java名*/
	private String javaName;
	
	/** 表编码方式*/
	private String chartset;
	
	/** 表注释（中文说明）*/
	private String comment;
	
	private Set<String> importClass = new HashSet<String>();
	
	@Override
	public String toString() {
		return "TableModel [tableName=" + tableName + ", javaName=" + javaName
				+ ", comment=" + comment + "]";
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getChartset() {
		return chartset;
	}

	public void setChartset(String chartset) {
		this.chartset = chartset;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getJavaName() {
		return javaName;
	}

	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}

	public Set<String> getImportClass() {
		return importClass;
	}

	public void setImportClass(Set<String> importClass) {
		this.importClass = importClass;
	}
	
}