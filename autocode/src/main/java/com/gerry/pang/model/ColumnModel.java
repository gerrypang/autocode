package com.gerry.pang.model;

import org.apache.commons.lang3.StringUtils;

import com.gerry.pang.consts.DictCode.DatabaseType;
import com.gerry.pang.consts.MySQLDataTypeMapping;

/**
 * 数据表字段-模型
 * 
 * @author pangguowei
 * @version v1.0.0 2018-06-01
 */
public class ColumnModel {
	/** 数据库字段code*/
	private String columnName;

	/** 映射java code*/
	private String javaName;

	/** 数据库类型 */
	private String columnType;

	/** 映射java类型 */
	private String javaType;

	/** 字段类型和长度 */
	private String columnTypeLength;

	/** 字段整数位长度 */
	private int colunSize;

	/** 字段小数位长度 */
	private int digits;
	
	/** 生成策略 */
	private String extra;

	/** 注释说明 */
	private String comment;

	/** 是否可为空 */
	private boolean nullable;

	/** 字段类型 */
	private String cloumnKey;

	/** 编码方式 */
	private String chartset;

	/** 字段导入类全称 */
	private String importClass;

	/**
	 * 转数据库类型为java类型
	 * 
	 * @param databaseType 数据库类型
	 * @param columnType 字段类型
	 */
	public void convertDatabaseToJavaType(String databaseType, String columnType) {
		String javaFullNameType = "";
		if (DatabaseType.MYSQL.equalsIgnoreCase(databaseType)) {
			String lowerCase = columnType.toLowerCase();
			javaFullNameType = MySQLDataTypeMapping.typeMapping.get(lowerCase);
			importClass = StringUtils.isNotBlank(javaFullNameType) ? javaFullNameType : "";
			this.setJavaType(StringUtils.substringAfterLast(javaFullNameType, "."));
		}
	}
	
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
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
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

	public String getColumnTypeLength() {
		return columnTypeLength;
	}

	public void setColumnTypeLength(String columnTypeLength) {
		this.columnTypeLength = columnTypeLength;
	}
	
	public String getImportClass() {
		return importClass;
	}

	public void setImportClass(String importClass) {
		this.importClass = importClass;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
}