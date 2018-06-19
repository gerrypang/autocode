package com.gerry.pang.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gerry.pang.model.DataSourceModel;
import com.gerry.pang.model.TableModel;

/**
 * Excel模板方式 - 代码自动生成准备类
 * 
 * @author gerry_pang
 * @version v1.0.0 2018-06-14
 */
public class ExcelSetUpHandler extends BaseSetUpHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelSetUpHandler.class);
	
	/**
	 * 加载数据源配置项
	 * 
	 * @return DataSourceModel 数据源配置模型
	 */
	public DataSourceModel loadExcelConfig() {
		logger.info(">>>>> loadDataSourceConfig() 开始加Excel源配置 ");
		propertyMap = this.getProperties();
		List<TableModel> tableList = new ArrayList<>();
		
		
		return null;
	}
	
}
