package com.gerry.pang.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.gerry.pang.consts.DictCode.AutoCodeProperties;
import com.gerry.pang.consts.DictCode.CommonCode;
import com.gerry.pang.consts.DictCode.GeneralClassType;
import com.gerry.pang.model.CommonModel;
import com.gerry.pang.model.DataSourceModel;
import com.gerry.pang.model.TableModel;
import com.gerry.pang.utils.CommonUtils;
import com.gerry.pang.utils.PropertiesUtils;

public class SetUpHandler {

	public static final Map<String,String> propertyMap = PropertiesUtils.getAllKeyValue(CommonCode.DATABASE_PROPERTIES);
	
	public DataSourceModel loadDataSourceConfig() {
		List<TableModel> tableList = new ArrayList<>();
		DataSourceModel dataSource = new DataSourceModel();
		// 使用静态块加载驱动程序
		dataSource.setDatabaseName(propertyMap.get("db.database"));
		dataSource.setDataType(propertyMap.get(AutoCodeProperties.JDBC_TYPE));
		dataSource.setPassword(propertyMap.get(AutoCodeProperties.JDBC_PASSWORD));
		dataSource.setUsername(propertyMap.get(AutoCodeProperties.JDBC_UERNAME));
		dataSource.setServerUrl(propertyMap.get(AutoCodeProperties.JDBC_URL));
		dataSource.setDriverClass(CommonUtils.getDBDriverClassByType(dataSource.getDataType()));
		if (StringUtils.isNotBlank(propertyMap.get(AutoCodeProperties.GENERAL_TABLES))) {
			String[] tables = propertyMap.get(AutoCodeProperties.GENERAL_TABLES).split(",");
			TableModel tableOne = null;
			if (tables != null && tables.length > 0) {
				for (String tableName : tables) {
					tableOne = new TableModel();
					tableOne.setTableName(tableName);
					tableList.add(tableOne);
				}
			}
		}
		return dataSource;
	}
	
	public CommonModel loadCommonConfig(){
		CommonModel common = new CommonModel();
		common.setAuthor(propertyMap.get(AutoCodeProperties.GENERAL_AUTHOR));
		if (StringUtils.isNotBlank(propertyMap.get(AutoCodeProperties.GENERAL_CLASS))) {
			String[] generals = propertyMap.get(AutoCodeProperties.GENERAL_CLASS).split(",");
			if (generals != null && generals.length > 0) {
				for (String gen : generals) {
					if (gen.equalsIgnoreCase(GeneralClassType.TYPE_ENTITY)) {
						common.setAssembler(true);
					}
					if (gen.equalsIgnoreCase(GeneralClassType.TYPE_DTO)) {
						common.setDto(true);
					}
					if (gen.equalsIgnoreCase(GeneralClassType.TYPE_ENUM)) {
						common.setEnum(true);
					}
					if (gen.equalsIgnoreCase(GeneralClassType.TYPE_Assembler)) {
						common.setAssembler(true);
					}
				}
			}
		}
		
		return null;
	}

}
