package com.gerry.pang.model;

import java.util.List;

/**
 * 数据表-模型
 * 
 * @author pangguowei
 * @version v1.0.0 2018-06-01
 */
public class TableModel{
	private String tableName;
	
	private List<ColumnModel> columns;
	
	private String chartset;
	
	private String comment;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
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
	
}