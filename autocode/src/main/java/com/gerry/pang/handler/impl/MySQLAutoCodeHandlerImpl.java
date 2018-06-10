package com.gerry.pang.handler.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gerry.pang.consts.DictCode.CommonCode;
import com.gerry.pang.consts.DictCode.DatabaseType;
import com.gerry.pang.consts.MySQLDataTypeMapping;
import com.gerry.pang.handler.AutoCodeHandler;
import com.gerry.pang.model.ColumnModel;
import com.gerry.pang.model.DataSourceModel;
import com.gerry.pang.model.TableModel;
import com.gerry.pang.utils.CommonDBUtils;
import com.gerry.pang.utils.CommonStringUtils;
import com.gerry.pang.utils.CommonUtils;

/**
 * MySQL实现-数据库处理类
 * 
 * @author gerry_pang
 * @version v1.0.0 2018-06-04
 */
public class MySQLAutoCodeHandlerImpl implements AutoCodeHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(MySQLAutoCodeHandlerImpl.class);

	/**
	 * 获取库中所有表模型
	 * 
	 * @param dataSource
	 * @return List<TableModel> 表模型list
	 */
	@Override
	public List<TableModel> getTableList(DataSourceModel dataSource) {
		List<TableModel> tableList = new ArrayList<TableModel>();
		StringBuilder sql = new StringBuilder(50);
		sql.append("SHOW TABLE STATUS FROM `").append(dataSource.getDatabaseName()).append("` WHERE Comment!='VIEW' ");
		if (CollectionUtils.isNotEmpty(dataSource.getTables())) {
			sql.append("AND name IN(");
			for (TableModel tableModel : dataSource.getTables()) {
				sql.append("'").append(tableModel.getTableName()).append("',");
			}
			sql.append("'')");
		}
		List<Object[]> queryResult = CommonDBUtils.queryList(dataSource, sql.toString());
		tableList = queryResult.stream().filter(n -> n != null && n.length == 18).map(n -> {
			TableModel tableOne = new TableModel();
			tableOne.setTableName(n[0].toString());
			try {
				tableOne.setJavaName(CommonStringUtils.formatClassName(n[0].toString()));
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			tableOne.setChartset(n[14].toString());
			tableOne.setComment(n[17].toString());
			return tableOne;
		}).collect(Collectors.toList());
		return tableList;
	}

	/**
	 * 获取表中所有字段模型
	 * 
	 * @param table
	 * @return List<ColumnModel> 字段模型list
	 */
	@Override
	public List<ColumnModel> getColumnOfTable(DataSourceModel dataSource, TableModel table) {
		List<ColumnModel> columnList = new ArrayList<ColumnModel>();
		StringBuilder sql = new StringBuilder(50);
		sql.append("SHOW FULL FIELDS FROM `").append(table.getTableName()).append("`");
		List<Object[]> queryResult = CommonDBUtils.queryList(dataSource, sql.toString());
		columnList = queryResult.stream().filter(n -> n != null).map(n -> {
			ColumnModel columnOne = new ColumnModel();
			columnOne.setColumnName(n[0].toString());
			try {
				columnOne.setJavaName(CommonStringUtils.formatVariableName(n[0].toString()));
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			columnOne.setColumnTypeLength(n[1].toString());
			columnOne.setColumnType(CommonUtils.getDataType(n[1].toString()));
			columnOne.setJavaType(MySQLDataTypeMapping.typeMapping.get(columnOne.getColumnType()));
			List<String> lengthList = CommonUtils.getDataLength(n[1].toString());
			if (CollectionUtils.isNotEmpty(lengthList)) {
				columnOne.setColunSize(Integer.valueOf(lengthList.get(0)));
				if (lengthList.size() == 2) {
					columnOne.setDigits(Integer.valueOf(lengthList.get(1)));
				}
			}
			columnOne.convertDatabaseToJavaType(DatabaseType.MYSQL, columnOne.getColumnType());
			columnOne.setChartset(n[2] == null ? "" : n[2].toString());
			columnOne.setNullable(n[3].toString().equalsIgnoreCase(CommonCode.YES) ? true : false);
			columnOne.setCloumnKey(n[4].toString());
			String idStategy = CommonUtils.getIdGenerateStrategy(DatabaseType.MYSQL, columnOne.getColumnType(), columnOne.getColunSize(), n[6].toString());
			columnOne.setExtra(idStategy);
			columnOne.setComment(n[8].toString());
			return columnOne;
		}).collect(Collectors.toList());
		return columnList;
	}
}
