package com.gerry.pang.model;

public class CommonModel {
	public String author;
	public boolean isDto = false;
	public boolean isEntity = false;
	public boolean isEnum = false;
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
}
