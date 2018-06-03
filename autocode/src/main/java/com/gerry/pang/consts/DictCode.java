package com.gerry.pang.consts;

/**
 * 字典码类
 */
public interface DictCode {

	/**
	 * 常用代码
	 */
	interface CommonCode {
		/** 默认编码方式 */
		public static final String DEFAULT_CHARTSET = "UTF-8";
		
		/** 格式化全格式日期  yyyy-MM-dd HH:mm:ss */
		public static final String FORMATE_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
		
		/** 格式化日期  yyyy-MM-dd */
		public static final String FORMATE_DATE = "yyyy-MM-dd";
		
		/** 格式化日期 HH:mm:ss */
		public static final String FORMATE_TIME = "HH:mm:ss";
		
		/** 默认数据库配置路径 */
		public static final String DATABASE_PROPERTIES = "src/main/resources/props/database.properties";
	}
	
	/**
	 * properties配置文件
	 */
	interface AutoCodeProperties {
		
		/** 数据库配置前缀 */
		public static final String PERFIX_JDBC = "jdbc.";
		
		/** 生成配置前缀 */
		public static final String PERFIX_GENERAL = "general.";
		
		/** 数据库类型 */
		public static final String JDBC_TYPE = "jdbc.type";
		
		/** 数据库驱动类 */
		public static final String JDBC_DRIVER = "jdbc.driver";
		
		/** 数据库url */
		public static final String JDBC_URL = "jdbc.url";
		
		/** 数据库用户名 */
		public static final String JDBC_UERNAME = "jdbc.username";
		
		/** 数据库密码 */
		public static final String JDBC_PASSWORD = "jdbc.password";
		
		/** 数据库端口 */
		public static final String JDBC_PORT = "jdbc.port";
		
		/** 数据库名 */
		public static final String JDBC_DATABASE = "jdbc.database";
		
		/** 生成表名 */
		public static final String GENERAL_TABLES = "general.tables";
		
		/** 生成类型 */
		public static final String GENERAL_CLASS = "general.class";
	}
	
	
	/**
	 * 数据库类型
	 */
	interface DatabaseType {
		/** MYSQL */
		public static final String MYSQL = "mysql";
		
		/** ORACLE */
		public static final String ORACLE = "oracle";
		
		/** DB2 */
		public static final String DB2 = "db2";
	}
	
	/**
	 * 数据库驱动类
	 */
	interface DatabaseDriverClass {
		/** MYSQL driverClassName */
		public static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";
		
		/** ORACLE driverClassName */
		public static final String ORACLE_DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
		
		/** DB2 driverClassName */
		public static final String DB2_DRIVER_CLASS = "com.ibm.db2.jcc.DB2Driver";
	}
	
	
	/**
	 * 数据库url
	 */
	interface DatabaseURL {
		/** MYSQL url */
		public static final String MYSQL_URL = "jdbc:mysql://ip:port/dbname?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";
		
		/** ORACLE url */
		public static final String ORACLE_URL = "jdbc:oracle:thin:@ip:port:dbname";
		
		/** DB2 url */
		public static final String DB2_URL = "jdbc:db2://ip:port/dbname";
	}
}
