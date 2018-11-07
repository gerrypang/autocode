package com.gerry.pang.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gerry.pang.utils.excel.annotation.ExcelField;

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
	
	/** 所有中剔除忽略的字段集合*/
	private List<ColumnModel> columnList = new ArrayList<ColumnModel>();
	
	/** 所有的字段集合 */
	private List<ColumnModel> allColumnList = new ArrayList<ColumnModel>();
	
	/** 忽略的字段集合 */
	private List<ColumnModel> ignoreColumnList = new ArrayList<ColumnModel>();
	
	@Override
	public String toString() {
		return "TableModel [tableName=" + tableName + ", javaName=" + javaName
				+ ", comment=" + comment + "]";
	}

	@ExcelField(title="归属公司")
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
	
	public List<ColumnModel> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<ColumnModel> columnList) {
		this.columnList = columnList;
	}

	public List<ColumnModel> getAllColumnList() {
		return allColumnList;
	}

	public void setAllColumnList(List<ColumnModel> allColumnList) {
		this.allColumnList = allColumnList;
	}

	public List<ColumnModel> getIgnoreColumnList() {
		return ignoreColumnList;
	}

	public void setIgnoreColumnList(List<ColumnModel> ignoreColumnList) {
		this.ignoreColumnList = ignoreColumnList;
	}
	
}