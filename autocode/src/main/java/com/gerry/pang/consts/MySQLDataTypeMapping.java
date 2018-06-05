package com.gerry.pang.consts;

import java.util.HashMap;
import java.util.Map;

public class MySQLDataTypeMapping {
	
	public static Map<String,String> typeMapping = new HashMap<String,String>();
	
	static {
		typeMapping.put("varchar", "java.lang.String");
		typeMapping.put("char", "java.lang.String");
		typeMapping.put("blob", "java.lang.byte");
		typeMapping.put("text", "java.lang.String");
		
		typeMapping.put("integer", "java.lang.Long");
		typeMapping.put("tinyint", "java.lang.Integer");
		typeMapping.put("smallint", "java.lang.Integer");
		typeMapping.put("mediumint", "java.lang.Integer");
		typeMapping.put("bit", "java.lang.Boolean");
		typeMapping.put("bigint", "java.math.BigInteger");
		typeMapping.put("float", "java.lang.Float");
		typeMapping.put("double", "java.lang.Double");
		typeMapping.put("double", "java.lang.Double");
		typeMapping.put("decimal", "java.math.BigDecimal");
		typeMapping.put("boolean", "java.lang.Integer");
		
		typeMapping.put("date", "java.util.Date");
		typeMapping.put("time", "java.sql.Time");
		typeMapping.put("datetime", "java.sql.Timestamp");
		typeMapping.put("timestamp", "java.sql.Timestamp");
		typeMapping.put("year", "java.util.Date");
	}
}
