package com.gerry.pang.model;

/**
 * 枚举类-模型
 * 
 * @author gerry_pang
 * @version v 1.0.0 2018-06-14
 */
public class EnumKeyValueModel {

	/** 代码 */
	public String code;
	
	/** 枚举值 */
	public String index;
	
	/** 枚举值说明 */
	public String comment;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
