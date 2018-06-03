package com.gerry.pang.model;

/**
 * 数据表字段-模型
 * 
 * @author pangguowei
 * @version v1.0.0 2018-06-01
 */
public class ColumnModel {
	private String columnName;
	private String javaName;
	private String columnType;
	private String javaType;
	private int colunSize;
	private int digits;
	private String comment;
	private boolean isNullable;
	private String cloumnKey;
	private String chartset;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isNullable() {
		return isNullable;
	}

	public void setNullable(boolean isNullable) {
		this.isNullable = isNullable;
	}

	public String getCloumnKey() {
		return cloumnKey;
	}

	public void setCloumnKey(String cloumnKey) {
		this.cloumnKey = cloumnKey;
	}

	public String getChartset() {
		return chartset;
	}

	public void setChartset(String chartset) {
		this.chartset = chartset;
	}

	public int getColunSize() {
		return colunSize;
	}

	public void setColunSize(int colunSize) {
		this.colunSize = colunSize;
	}

	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}

	public String getJavaName() {
		return javaName;
	}

	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
}