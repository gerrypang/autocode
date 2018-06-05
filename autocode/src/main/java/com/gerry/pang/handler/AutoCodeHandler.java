package com.gerry.pang.handler;

import java.util.List;

import com.gerry.pang.model.ColumnModel;
import com.gerry.pang.model.DataSourceModel;
import com.gerry.pang.model.TableModel;

/**
 * 数据库处理接口类 
 * 
 * @author gerry_pang
 * @version v1.0.0 2018-06-04
 */
public interface AutoCodeHandler {
	
	/**
	 * 获取库中所有表模型
	 * 
	 * @param dataSource
	 * @return List<TableModel> 表模型list
	 */
	public List<TableModel> getTableList(DataSourceModel dataSource);
	
	/**
	 * 获取表中所有字段模型
	 * 
	 * @param dataSource
	 * @param table
	 * @return List<ColumnModel> 字段模型list
	 */
	public List<ColumnModel> getColumnOfTable(DataSourceModel dataSource, TableModel table);
	
	
}
