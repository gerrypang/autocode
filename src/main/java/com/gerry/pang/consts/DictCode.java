package com.gerry.pang.consts;

/**
 * 字典码类
 */
public interface DictCode {

	/**
	 * 常用代码
	 */
	interface CommonCode {
		public static final String YES = "yes";
		public static final String NO = "no";
		
		/** 默认编码方式 */
		public static final String DEFAULT_CHARTSET = "UTF-8";
		
		/** 格式化全格式日期  yyyy-MM-dd HH:mm:ss */
		public static final String FORMATE_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
		
		/** 格式化日期  yyyy-MM-dd */
		public static final String FORMATE_DATE = "yyyy-MM-dd";
		
		/** 格式化日期 HH:mm:ss */
		public static final String FORMATE_TIME = "HH:mm:ss";
		
		/** 默认数据库配置路径 */
		public static final String CONFIG_PROPERTIES_PATH = "src/main/resources/config.properties";
		
		/** 模板配置路径 */
		public static final String TEMPLATE_PATH = "/templates";
		
		/** 生成文件后缀名 */
		public static final String SUFFIX = ".java";
	}
	
	/**
	 * properties配置文件
	 */
	interface AutoCodeProperties {
		
		/** 生成类型 */
		public static final String GENERAL_CLASS = "general.class";

		/** 生成类型 */
		public static final String GENERAL_AUTHOR = "general.author";
		
		/** 生成基础包路径 */
		public static final String GENERAL_PACKAGE = "general.package";
		
		/** 生成路径 */
		public static final String GENERAL_PATH = "general.path";
		
		/** 从那种方式生成 */
		public static final String GENERAL_TYPE = "general.type";
		
		
		/** 数据库配置前缀 */
		public static final String PERFIX_JDBC = "jdbc";
		
		/** 生成配置前缀 */
		public static final String PERFIX_GENERAL = "general";
		
		/** 枚举配置前缀 */
		public static final String PERFIX_ENUM = "enum";
		
		/** excel配置前缀 */
		public static final String PERFIX_EXCEL = "excel";
		
		
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
		
		/** jdbc生成表名 */
		public static final String JDBC_TABLES = "jdbc.tables";
		
		
		/** excel url */
		public static final String EXCEL_URL = "excel.url";
		
		/** excel生成表名 */
		public static final String EXCEL_TABLES = "excel.tables";
		
		/** excel生成sqlType */
		public static final String EXCEL_SQLTYPE = "excel.sqlType";
	}
	
	
	/**
	 * 从那种生成类型
	 */
	interface GeneralFrom {
		/** 从数据库 */
		public static final String FROM_JDBC = "jdbc";
		
		/** 从excel */
		public static final String FROM_EXCEL = "excel";
	}
	
	
	/** 
	 * 生成类型 
	 */
	interface GeneralClassType {
		public static final String TYPE_DTO = "dto";

		public static final String TYPE_ENTITY = "entity";
		
		public static final String TYPE_ENUM = "enum";
		
		public static final String TYPE_ASSEMBLER = "assembler";

		public static final String TYPE_MAPPER = "mapper";

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
		public static final String MYSQL_URL = "?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";
		
		/** ORACLE url */
		public static final String ORACLE_URL = "jdbc:oracle:thin:@ip:port:dbname";
		
		/** DB2 url */
		public static final String DB2_URL = "jdbc:db2://ip:port/dbname";
	}
	
	
	interface MySQLKeyGeneralStrategy {
		public static final String AUTO = "auto_increment";
	}
}
