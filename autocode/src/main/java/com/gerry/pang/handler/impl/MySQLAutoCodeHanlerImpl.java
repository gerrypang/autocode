package com.gerry.pang.handler.impl;

import java.util.List;

import com.gerry.pang.handler.AutoCodeHandler;
import com.gerry.pang.model.ColumnModel;
import com.gerry.pang.model.DataSourceModel;
import com.gerry.pang.model.TableModel;
import com.gerry.pang.utils.CommonDBUtils;

public class MySQLAutoCodeHanlerImpl implements AutoCodeHandler {

	@Override
	public List<TableModel> getTableList(DataSourceModel dataSource) {
		StringBuilder sql = new StringBuilder(100);
		sql.append("SHOW TABLE STATUS FROM ? ");
		List<Object[]> tableList = CommonDBUtils.queryList(sql.toString(), dataSource.getDatabaseName());
		return null;
	}

	@Override
	public List<ColumnModel> getColumnOfTable(TableModel table) {

		return null;
	}
}
