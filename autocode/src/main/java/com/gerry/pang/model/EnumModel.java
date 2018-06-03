package com.gerry.pang.model;

/**
 * 枚举类-模型
 * 
 * @author pangguowei
 * @version v 1.0.0 2018-06-01
 */
public class EnumModel {
	/** 枚举英文名 */
	private String codeName;
	/** 枚举值 */
	private String codeValue;
	/** 中文解释 */
	private String codeComment;

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getCodeComment() {
		return codeComment;
	}

	public void setCodeComment(String codeComment) {
		this.codeComment = codeComment;
	}
}
