package com.gerry.pang.handler;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gerry.pang.auto.CodeGenerator;
import com.gerry.pang.consts.DictCode.AutoCodeProperties;
import com.gerry.pang.model.CommonModel;
import com.gerry.pang.model.DataSourceModel;
import com.gerry.pang.model.EnumModel;

import freemarker.template.Configuration;

/**
 * 代码生成器主入口类
 *
 * @author gerry_pang
 * @version v1.0.0 2018-06-04
 */
public class MainHandler {

	private static final Logger logger = LoggerFactory.getLogger(DataBaseSetUpHandler.class);

	private DataSourceModel dataSource;

	private CommonModel commonData;

	private List<EnumModel> enumData = null;

	private Configuration configuration;

	/**
	 * 初始化准备工作
	 * @throws IOException
	 */
	public void init() throws IOException {
		BaseSetUpHandler setUp = new BaseSetUpHandler();
		commonData = setUp.loadCommonConfig();
		configuration = setUp.setUpFreemarkerTemplateConfig(commonData);
		setUp.loadExtendJar();
	}

	/**
	 * 代码生成方法
	 * @throws IOException
	 */
	public void generate() throws IOException {
		this.init();
		// 从数据库方式生成代码
		if(AutoCodeProperties.PERFIX_JDBC.equalsIgnoreCase(commonData.getType())){
			DataBaseSetUpHandler dbSetUp = new DataBaseSetUpHandler();
			dataSource = dbSetUp.loadDataSourceConfig();
			enumData = dbSetUp.loadEnumConfig();
		}
		// 从excel方法生成代码
		if(AutoCodeProperties.PERFIX_EXCEL.equalsIgnoreCase(commonData.getType())){
//			DataBaseSetUpHandler dbSetUp = new DataBaseSetUpHandler();
//			dataSource = dbSetUp.loadDataSourceConfig();
//			enumData = dbSetUp.loadEnumConfig();
		}
		CodeGenerator generator = new CodeGenerator();
		if (commonData.isEntity()) {
			// 生成实体类（JPA包括注解）
			logger.info(">>>>> 开始生成Entity类 ");
			generator.generateEntityClass(dataSource, configuration, commonData);
		}
		if (commonData.isDto()) {
			// 生成DTO类
			logger.info(">>>>> 开始生成DTO类 ");
			generator.generateDtoClass(dataSource, configuration, commonData);
		}
		if (commonData.isAssembler()) {
			// 生成entity和DTO之间转换类
			logger.info(">>>>> 开始生成Entity和DTO之间转换类 ");
			generator.generateAssemblerClass(dataSource, configuration, commonData);
		}
		if (commonData.isMapper()) {
			// 生成Mapper类
			logger.info(">>>>> 开始生成Mapper类 ");
			generator.generateMapperXML(dataSource, configuration, commonData);
			generator.generateMapperClass(dataSource, configuration, commonData);
		}
		if (commonData.isEnum()) {
			// 生成枚举类
			logger.info(">>>>> 开始生成枚举类 ");
			generator.generateEnumClass(configuration, commonData, enumData);
		}
	}

	/**
	 * 程序主入口
	 * @param args
	 */
	public static void main(String[] args) {
		MainHandler main = new MainHandler();
		try {
			logger.info("===================================================");
			logger.info("=       代码生成器 v1.0.0 Author:gerry pang        =");
			logger.info("===================================================");
			logger.info(">>>>> 生成代码器启动开始 ");
			main.generate();
			logger.info(">>>>> 生成代码器工作完成 ");
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
}
