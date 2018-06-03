package com.gerry.pang.handler;

import java.util.List;

import com.gerry.pang.model.ColumnModel;
import com.gerry.pang.model.DataSourceModel;
import com.gerry.pang.model.TableModel;

public interface AutoCodeHandler {
	
	public List<TableModel> getTableList(DataSourceModel dataSource);
	
	public List<ColumnModel> getColumnOfTable(TableModel table);
	
	
	
}
