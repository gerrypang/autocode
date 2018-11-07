package com.gerry.pang.utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gerry.pang.consts.DictCode.DatabaseURL;
import com.gerry.pang.model.DataSourceModel;

/**
 * 数据库操作工具类
 * 
 * @author gerry_pang
 * @version v1.0.0 2018-06-01
 */
public class CommonDBUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonDBUtils.class);
	
	/**
	 * 定义一个获取数据库连接的方法
	 * 
	 * @return 
	 * @version v1.0.0  2018-06-01
	 */
	public static Connection getConnection(String url, String username, String password) {
	    Connection conn = null;
	    try {
	        conn = DriverManager.getConnection(url, username, password);
	    } catch (SQLException e) {
	        logger.error("获取连接失败 {}", e.getMessage());
	    }
	    return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param rs
	 * @param stat
	 * @param conn 
	 * @version v1.0.0 2018-06-01
	 */
	public static void close(ResultSet rs, Statement stat, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			logger.error("关闭连接失败 {}", e.getMessage());
		}
	}
	
	/**
	 *
	 *
	 * @param sql
	 * @param param
	 * @param c
	 * @return
	 */
	public static <T> String queryFieldByID(DataSourceModel dataSource, String sql, String param, Class<T> c) {
		Connection connection = null;
		String result = null;
		try {
			connection = getConnection(dataSource.getServerFullUrl(), dataSource.getUsername(), dataSource.getPassword());
	    	QueryRunner  query = new QueryRunner();
	    	logger.info("The execute SQL is {},and the param is {}", sql, param);
			T t = query.query(connection, sql, new ScalarHandler<T>(), new BigDecimal(param));
			if (t != null) {
				if (t instanceof BigDecimal) {
					result = ((BigDecimal) t).toBigInteger().toString();
				} else {
					result = t.toString();
				}
			}
			logger.info("result is {}", result);
		} catch (Exception e) {
			logger.error("查询异常！{}", e);
		} finally {
			if (null != connection) {
				// 关闭数据库连接
				DbUtils.closeQuietly(connection);
			}
		}
		// 创建SQL执行工具
		return result;
	}

	/**
	 *
	 *
	 * @param connMap
	 * @param sql
	 * @param c
	 * @param param
	 * @return
	 */
	public static <T> String queryField(DataSourceModel dataSource, String sql, Class<T> c, Object... param){
		Connection connection = null;
		String result = null;
		try {
			connection = getConnection(dataSource.getServerFullUrl(), dataSource.getUsername(), dataSource.getPassword());
	    	QueryRunner  query = new QueryRunner();
	    	logger.info("The execute SQL is {},and the param is {}", sql, param);
	    	T t = query.query(connection, sql, new ScalarHandler<T>(), param);
			if (t != null) {
				if (t instanceof BigDecimal) {
					result = ((BigDecimal) t).toBigInteger().toString();
				} else {
					result = t.toString();
				}
	    	}
			logger.info("result is {}", result);
		} catch (Exception e) {
			logger.error("查询异常！{}", e);
		} finally {
			if (null != connection) {
				// 关闭数据库连接
				DbUtils.closeQuietly(connection);
			}
		}
		//创建SQL执行工具
		return result;
	}

	/**
	 *
	 *
	 * @param connMap
	 * @param sql
	 * @param param
	 * @return
	 */
	public static Map<String,Object> queryFields(DataSourceModel dataSource, String sql, Object... param){
		Connection connection = null;
		Map<String,Object> result = null;
		try {
			connection = getConnection(dataSource.getServerFullUrl(), dataSource.getUsername(), dataSource.getPassword());
	    	QueryRunner  query = new QueryRunner();
	    	logger.info("The execute SQL is {},and the param is {}", sql, param);
	    	result = query.query(connection, sql, new MapHandler(), param);
			logger.info("result is {}", result);
		} catch (Exception e) {
			logger.error("查询异常！{}", e);
		} finally {
			if (null != connection) {
				// 关闭数据库连接
				DbUtils.closeQuietly(connection);
			}
		}
		// 创建SQL执行工具
		return result;
	}

	/**
	 *
	 *
	 * @param connMap
	 * @param sql
	 * @param c
	 * @param param
	 * @return
	 */
	public static int update(DataSourceModel dataSource, String sql, Object... param){
		Connection connection = null;
		int result = 0;
		try {
			connection = getConnection(dataSource.getServerFullUrl(), dataSource.getUsername(), dataSource.getPassword());
	    	QueryRunner  query = new QueryRunner();
	    	logger.info("The execute SQL is {},and the param is {}", sql, param);
	    	result = query.update(connection, sql, param);
	    	logger.info("result is {}" , result);
		} catch (Exception e) {
			logger.error("查询异常！{}",e);
		} finally {
			if (null != connection) {
				//关闭数据库连接
				DbUtils.closeQuietly(connection);
			}
		}
		return result;
	}

	/**
	 *
	 *
	 * @param connMap
	 * @param sql
	 * @param param
	 */
	public static List<Object[]> queryList(DataSourceModel dataSource, String sql, Object... param){
		Connection connection = null;
		List<Object[]> result = null;
		try {
			connection = getConnection(dataSource.getServerFullUrl(), dataSource.getUsername(), dataSource.getPassword());
	    	QueryRunner  query = new QueryRunner();
	    	logger.info("The execute SQL is {},and the param is {}",sql, param);
	    	result = query.query(connection, sql, new ArrayListHandler(), param);
	    	logger.info("result is {}" , result);
		} catch (Exception e) {
			logger.error("查询异常！{}",e);
		} finally {
			if (null != connection) {
				//关闭数据库连接
				DbUtils.closeQuietly(connection);
			}
		}
		return result;
	}
	
}
