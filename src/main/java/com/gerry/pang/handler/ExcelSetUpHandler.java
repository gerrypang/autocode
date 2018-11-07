package com.gerry.pang.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gerry.pang.consts.DictCode.AutoCodeProperties;
import com.gerry.pang.model.ColumnModel;
import com.gerry.pang.model.DataSourceModel;
import com.gerry.pang.model.TableModel;
import com.gerry.pang.utils.excel.ImportExcel;

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
		String excelPath = propertyMap.get(AutoCodeProperties.EXCEL_URL);
		DataSourceModel dataSource = new DataSourceModel();
		List<TableModel> tableList = new ArrayList<>();
		try {
//			if(StringUtils.startsWith(excelPath, ".")) { 
//				String currentWork = CommonUtils.getCruuentWorkUrl();
//			}
				
			ImportExcel ei = new ImportExcel(excelPath, 2);
			int totalSheetNum = ei.getAllSheetNum();
			for (int i = 0; i < totalSheetNum; i++) {
				List<ColumnModel> columnOne = ei.getDataList(i, ColumnModel.class);
				List<Object[]> Cols = ei.getDataList(i);
			}
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		dataSource.setTables(tableList);
		return dataSource;
	}
	
	private void loadEnumConfig(List<Object[]> Cols){
		
	}
	
	public static void main(String[] args) {
		ExcelSetUpHandler esu = new ExcelSetUpHandler();
		esu.loadExcelConfig();
	}
}
