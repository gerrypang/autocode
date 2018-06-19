package com.gerry.pang.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.gerry.pang.consts.DictCode.AutoCodeProperties;
import com.gerry.pang.model.DataSourceModel;
import com.gerry.pang.model.EnumModel;
import com.gerry.pang.model.TableModel;

/**
 * 数据库方式 - 代码自动生成准备类
 * 
 * @author gerry_pang
 * @version v1.0.0 2018-06-04
 */
public class DataBaseSetUpHandler extends BaseSetUpHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(DataBaseSetUpHandler.class);
	
	/**
	 * 加载数据源配置项
	 * 
	 * @return DataSourceModel 数据源配置模型
	 */
	public DataSourceModel loadDataSourceConfig() {
		logger.info(">>>>> loadDataSourceConfig() 开始加载数据源配置 ");
		propertyMap = this.getProperties();
		List<TableModel> tableList = new ArrayList<>();
		DataSourceModel dataSource = new DataSourceModel();
		// 使用静态块加载驱动程序
		dataSource.setDatabaseName(propertyMap.get(AutoCodeProperties.JDBC_DATABASE));
		dataSource.setDataType(propertyMap.get(AutoCodeProperties.JDBC_TYPE));
		dataSource.setPassword(propertyMap.get(AutoCodeProperties.JDBC_PASSWORD));
		dataSource.setUsername(propertyMap.get(AutoCodeProperties.JDBC_UERNAME));
		dataSource.setServerUrl(propertyMap.get(AutoCodeProperties.JDBC_URL));
		dataSource.setDriverClass(propertyMap.get(AutoCodeProperties.JDBC_DRIVER));
		if (StringUtils.isNotBlank(propertyMap.get(AutoCodeProperties.JDBC_TABLES))) {
			String[] tables = propertyMap.get(AutoCodeProperties.JDBC_TABLES).split(",");
			TableModel tableOne = null;
			if (tables != null && tables.length > 0) {
				for (String tableName : tables) {
					tableOne = new TableModel();
					tableOne.setTableName(tableName);
					tableList.add(tableOne);
				}
				dataSource.setTables(tableList);
			}
		}
		logger.info(">>>>> 完成加载数据源配置 ");
		return dataSource;
	}

	public List<EnumModel> loadEnumConfig() {
		propertyMap = this.getProperties();
		String enumJson = propertyMap.get(AutoCodeProperties.PERFIX_ENUM);
		List<EnumModel> enumList = new ArrayList<EnumModel>();
		if (StringUtils.isNotBlank(enumJson)) {
			enumList = JSON.parseArray(enumJson, EnumModel.class);
		}
		return enumList;
	}
	
}
