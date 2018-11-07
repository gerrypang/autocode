package com.gerry.pang.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.gerry.pang.consts.DictCode.GeneralClassType;

/**
 * 枚举类-模型
 * 
 * @author gerry_pang
 * @version v 1.0.0 2018-06-01
 */
public class EnumModel {
	
	/** 枚举名 */
	private String javaName;
	
	/** 中文解释 */
	private String javaComment;
	
	@JSONField(name="keyValues")
	private List<EnumKeyValueModel> keyValues = new ArrayList<>();

	public String getJavaName() {
		return javaName;
	}

	public void setJavaName(String javaName) {
		this.javaName = StringUtils.containsIgnoreCase(javaName, GeneralClassType.TYPE_ENUM) ? 
				javaName: javaName + StringUtils.capitalize(GeneralClassType.TYPE_ENUM); ;
	}

	public void addKeyValue(EnumKeyValueModel keyValue) {
		keyValues.add(keyValue);
	}

	public String getJavaComment() {
		return javaComment;
	}

	public void setJavaComment(String javaComment) {
		this.javaComment = javaComment;
	}

	public List<EnumKeyValueModel> getKeyValues() {
		return keyValues;
	}

	public void setKeyValues(List<EnumKeyValueModel> keyValues) {
		this.keyValues = keyValues;
	}
}
