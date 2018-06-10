package com.gerry.pang.model;


/**
 * 生成通用配置模型
 * 
 * @author gerry_pang
 * @version v1.0.0 2018-06-01
 */
public class CommonModel {
	
	/** 生产作者 */
	public String author;

	/** 生成基础包 */
	public String packageName;
	
	/** 生成路径 */
	public String path;

	/** 是否生成DTO类 */
	public boolean isDto = false;
	
	public String dtoExtendClass = "";

	/** 是否生成Entity类 */
	public boolean isEntity = false;
	
	public String entityExtendClass = "";

	/** 是否生成Enum类 */
	public boolean isEnum = false;
	
	public String enumExtendClass = "";

	/** 是否生成Assember类 */
	public boolean isAssembler = false;
	
	public String assemblerExtendClass = "";

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isDto() {
		return isDto;
	}

	public void setDto(boolean isDto) {
		this.isDto = isDto;
	}

	public boolean isEntity() {
		return isEntity;
	}

	public void setEntity(boolean isEntity) {
		this.isEntity = isEntity;
	}

	public boolean isEnum() {
		return isEnum;
	}

	public void setEnum(boolean isEnum) {
		this.isEnum = isEnum;
	}

	public boolean isAssembler() {
		return isAssembler;
	}

	public void setAssembler(boolean isAssembler) {
		this.isAssembler = isAssembler;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDtoExtendClass() {
		return dtoExtendClass;
	}

	public void setDtoExtendClass(String dtoExtendClass) {
		this.dtoExtendClass = dtoExtendClass;
	}

	public String getEntityExtendClass() {
		return entityExtendClass;
	}

	public void setEntityExtendClass(String entityExtendClass) {
		this.entityExtendClass = entityExtendClass;
	}

	public String getEnumExtendClass() {
		return enumExtendClass;
	}

	public void setEnumExtendClass(String enumExtendClass) {
		this.enumExtendClass = enumExtendClass;
	}

	public String getAssemblerExtendClass() {
		return assemblerExtendClass;
	}

	public void setAssemblerExtendClass(String assemblerExtendClass) {
		this.assemblerExtendClass = assemblerExtendClass;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
