package com.gerry.pang.mybatis;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * Mybaits generator 自定义插件
 * @author pangguowei
 */
public class JPAPlugin extends PluginAdapter {
	
    /**
     * 验证参数是否有效
     * @param warnings
     * @return
     */
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	/**
	 * 自定义生成字段配置
	 */
	@Override
	public boolean modelFieldGenerated(Field field,
			TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		// 获取基础变量
		String remarks = introspectedColumn.getRemarks();
		String columnName = introspectedColumn.getActualColumnName();
		Boolean isNullBale = introspectedColumn.isNullable();
		int length = introspectedColumn.getLength();
		String columnType = introspectedColumn.getJdbcTypeName();
		String columnDefinition = length == 0 ? columnType : columnType + "(" + length + ")";
		StringBuffer columnAnnotation = new StringBuffer(100);
		
		Boolean isPrimary = introspectedColumn.isIdentity();
		boolean isStringColumn = introspectedColumn.isStringColumn();
		Boolean isSequenceColumn = introspectedColumn.isSequenceColumn();
		String keyStrategy = "assigned";
		// 添加主键注解
		if (isPrimary) {
			field.addAnnotation("@Id");
			field.addAnnotation("@GeneratedValue(generator = \"" + columnName + "KeyGenerate\")");
			if (isStringColumn) {
				keyStrategy = length >= 32 ? "uuid" : "assigned";
			}
			if (isSequenceColumn) {
				keyStrategy = "sequence";
			}
			field.addAnnotation("@GenericGenerator(name = \"" + columnName + "KeyGenerate\", strategy = \""+ keyStrategy +"\")");
		}
		// 添加字段注解
		columnAnnotation.append("@Column(name = \"").append(columnName)
			.append("\", nullable = '").append(isNullBale)
			.append("\', columnDefinition = \"").append(columnDefinition)
			.append(" comment '").append(remarks).append("'\")");
		field.addAnnotation(columnAnnotation.toString());
		
		return true;
	}

	/**
	 * 自定义生成类配置
	 */
	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		// add jpa Entity Annotation
		topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.persistence.Entity"));
		topLevelClass.addAnnotation("@Entity");
		// add jpa Table Annotation
		topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.persistence.Table"));
		String tableName = introspectedTable.getTableConfiguration().getTableName();
		topLevelClass.addAnnotation("@Table(name = \"" + tableName + "\")");
		// import jpa other annotation
		topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.persistence.Column"));
		topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.persistence.GeneratedValue"));
		topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.persistence.Id"));
		topLevelClass.addImportedType(new FullyQualifiedJavaType("org.hibernate.annotations.GenericGenerator"));
		return true;
	}
}
