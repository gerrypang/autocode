package com.gerry.pang.model;


/**
 * 生成通用配置模型
 * 
 * @author pangguowei
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

	/** 是否生成Entity类 */
	public boolean isEntity = false;

	/** 是否生成Enum类 */
	public boolean isEnum = false;

	/** 是否生成Assember类 */
	public boolean isAssembler = false;

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
}
