package com.gerry.pang.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class CommonStringUtils {
	
	/** 验证类/变量名 正则表达式 */
	private static final String LEGAL_CV_NAME = "^[a-zA-Z][a-zA-Z0-9_]*$";
	
	/** 类/变量名 最大长度 */
	private static final int CV_MAX_LENGTH = 50;
	
	/** 验证包名 正则表达式*/
	private static final String LEGAL_PACKAGE_NAME = "[a-zA-Z]+[0-9a-zA-Z_]*(\\.[a-zA-Z]+[0-9a-zA-Z_]*)*";
	
	/** 类/变量名 最大长度 */
	private static final int PACKAGE_MAX_LENGTH = 300;
	
	/**
	 * 格式化类名
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public  static String formatClassName(String className) throws Exception {
		if (StringUtils.isBlank(className)) {
			throw new Exception("类名不能为空！");
		}
		if (!Pattern.matches(LEGAL_CV_NAME, className)) {
			throw new Exception("类名只能以字母开头，只能包含英文字母、数字、下划线！");
		}
		if (className.length() > CV_MAX_LENGTH) {
			throw new Exception("类名长度不能超过" + CV_MAX_LENGTH + "个字符！");
		}
		return formatSpell(className, true);
	}
	
	/**
	 * 格式化变量名
	 * @param variableName
	 * @return
	 * @throws Exception
	 */
	public  static String formatVariableName(String variableName) throws Exception {
		if (StringUtils.isBlank(variableName)) {
			throw new Exception("变量名不能为空！");
		}
		if (!Pattern.matches(LEGAL_CV_NAME, variableName)) {
			throw new Exception("变量名只能以字母开头，只能包含英文字母、数字、下划线！");
		}
		if (variableName.length() > 50) {
			throw new Exception("变量名长度不能超过50个字符！");
		}
		return formatSpell(variableName, false);
	}
	
	/**
	 * 格式化包名
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public  static String formatPackageName(String packageName) throws Exception {
		if (StringUtils.isBlank(packageName)) {
			throw new Exception("包名不能为空！");
		}
		if (!Pattern.matches(LEGAL_PACKAGE_NAME, packageName)) {
			throw new Exception("包名只能以字母开头，只能包含英文字母、点！");
		}
		if (packageName.length() > PACKAGE_MAX_LENGTH) {
			throw new Exception("包名长度不能超过" + PACKAGE_MAX_LENGTH + "个字符！");
		}
		return formatSpell(packageName, false);
	}
	
	/**
	 * 类/变量/包 通用方法
	 * @param cvName
	 * @return
	 */
	private static String formatSpell(String spell, boolean firstUpper){
		StringBuilder split = new StringBuilder(50);
		char[] allChar = spell.toCharArray();
		for (int i = 0; i < allChar.length; i++) {
			if (firstUpper && i==0) {
				allChar[i] -= 32;
			}
			// 删除下划线，并将后面字母改为大写
			if (allChar[i] == '_') {
				i += 1;
				if (allChar[i] >= 'a' && allChar[i] <= 'z') {
					allChar[i] -= 32;
					split.append(allChar[i]);
					continue;
				}
			}
			split.append(allChar[i]);
		}
		return split.toString();
	}
	
	public static void main(String[] args) throws Exception {
		String a = formatClassName("class_bame");
		String v = formatVariableName("class_bame");
		String p = formatPackageName("class.Mame");
		System.out.println(a);
		System.out.println(v);
		System.out.println(p);
	}
}
