package com.gerry.pang.consts;

import java.util.HashMap;
import java.util.Map;

public class MySQLDataTypeMapping {
	
	public static Map<String, String> typeMapping = new HashMap<String, String>();
	public static Map<String, String> generateKeyStategyMapping = new HashMap<String, String>();
	
	static {
		typeMapping.put("varchar", "java.lang.String");
		typeMapping.put("char", "java.lang.String");
		typeMapping.put("blob", "java.lang.byte");
		typeMapping.put("text", "java.lang.String");
		typeMapping.put("mediumtext", "java.lang.String");
		
		typeMapping.put("int", "java.lang.Integer");
		typeMapping.put("integer", "java.lang.Long");
		typeMapping.put("tinyint", "java.lang.Integer");
		typeMapping.put("smallint", "java.lang.Integer");
		typeMapping.put("mediumint", "java.lang.Integer");
		typeMapping.put("bit", "java.lang.Boolean");
		typeMapping.put("bigint", "java.lang.Long");
		typeMapping.put("float", "java.lang.Float");
		typeMapping.put("double", "java.lang.Double");
		typeMapping.put("double", "java.lang.Double");
		typeMapping.put("decimal", "java.math.BigDecimal");
		typeMapping.put("boolean", "java.lang.Integer");
		
		typeMapping.put("date", "java.util.Date");
		typeMapping.put("time", "java.util.Date");
		typeMapping.put("datetime", "java.util.Date");
		typeMapping.put("timestamp", "java.util.Date");
		typeMapping.put("year", "java.util.Date");
		
		generateKeyStategyMapping.put("auto_increment", "identity");
		generateKeyStategyMapping.put("char", "assigned");
		generateKeyStategyMapping.put("varchar", "assigned");
		generateKeyStategyMapping.put("integer", "assigned");
		generateKeyStategyMapping.put("bigint", "assigned");
		generateKeyStategyMapping.put("decimal", "assigned");
	}
}
