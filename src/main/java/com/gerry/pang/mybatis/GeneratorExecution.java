package com.gerry.pang.mybatis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneratorExecution {

	private static final Logger logger = LoggerFactory.getLogger(GeneratorExecution.class);
	
	public static void generator() {
		List<String> warnings = new ArrayList<String>();
		try {
			// 导入配置表mybatis-generator.xml
			File configFile = new File("src/main/resources/mybatis-generator.xml");
			// 解析
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(configFile);
			// 是否覆盖
			DefaultShellCallback dsc = new DefaultShellCallback(true);
			MyBatisGenerator mg = new MyBatisGenerator(config, dsc, warnings);
			mg.generate(null);
		} catch (Exception e) {
			logger.error("Mybatis 生成出现异常：{}", e.toString());
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GeneratorExecution.generator();
		logger.error("========== Mybatis 生成完成 ========== ");
	}

}
