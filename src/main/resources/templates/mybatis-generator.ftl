<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
  PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
  "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
	<context id="autocode" targetRuntime="MyBatis3" defaultModelType="flat">
	    <!-- 序列化插件 -->
	    <plugin type="org.mybatis.generator.plugins.SerializablePlugin" />
		<!-- 自动生成toString方法 -->
        <plugin type="org.mybatis.generator.plugins.ToStringPlugin"/>
        <!-- 自动生成equals方法和hashcode方法 -->
        <plugin type="org.mybatis.generator.plugins.EqualsHashCodePlugin"/>
        <!-- 自动生成equals方法和hashcode方法 -->
        <plugin type="com.gerry.pang.mybatis.JPAPlugin"/>
        
		<commentGenerator>
			<!-- 是否去除自动生成的注释 true：是 ： false:否 -->
			<property name="suppressAllComments" value="false" />
			<!-- 设置编码为UTF-8 -->
			<property name="javaFileEncoding" value="UTF-8"/>
		</commentGenerator>
		
		<!--数据库连接的信息：驱动类、连接地址、用户名、密码 -->
		<jdbcConnection driverClass="${jdbc.driverClass}"
			connectionURL="${jdbc.serverUrl}?serverTimezone=GMT%2B8&amp;useUnicode=true&amp;characterEncoding=UTF-8" 
			userId="${jdbc.username}" password="${jdbc.password}">
		</jdbcConnection>

		<!-- 默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer true，
		把JDBC DECIMAL 和 NUMERIC 类型解析为java.math.BigDecimal -->
		<javaTypeResolver>
			<property name="forceBigDecimals" value="false" />
		</javaTypeResolver>

		<!-- targetProject:生成PO类的位置 -->
		<javaModelGenerator targetPackage="${common.packageName}.pojo"  targetProject="${common.basePath}">
			<!-- enableSubPackages:是否让schema作为包的后缀 -->
			<property name="enableSubPackages" value="false" />
			<!-- 从数据库返回的值被清理前后的空格 -->
			<property name="trimStrings" value="true" />
			<#if common.entityExtendClass != "" >
			<!-- 集成的基类 -->
			<property name="rootClass" value="${common.entityExtendClass}"/>
			</#if>
		</javaModelGenerator>
		
		<!-- targetPackage:mapper映射文件生成的位置 -->
		<sqlMapGenerator targetPackage="${common.packageName}.mapper" targetProject="${common.basePath}">
			<property name="enableSubPackages" value="false" />
		</sqlMapGenerator>
		
		<!-- targetPackage：mapper接口的生成位置 -->
		<javaClientGenerator type="XMLMAPPER" targetPackage="${common.packageName}.mapper" targetProject="${common.basePath}">
			<property name="enableSubPackages" value="false" />
		</javaClientGenerator>

		<!-- 指定表 emp 这里不指定schema，逆向工程会查询sysuser都有哪些schema，对每个schema生成对象 -->
		<#list tableList as x>
		<table tableName="${x.tableName}" domainObjectName="${x.javaName}">
			<#list x.columnList as n>
			<#if n.cloumnKey == "pri" || n.cloumnKey == "PRI" > 
			<generatedKey column="${n.columnName}" sqlStatement="${common.type}" identity="true"/>
			</#if>
			</#list>
		</table>
		</#list>

	</context>
</generatorConfiguration>
