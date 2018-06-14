package ${packageName}.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：${enum.javaComment} 枚举类
 * 
 * @author ${author}
 * ${generateDateTime}
 */
public enum ${enum.javaName} {

	<#list enum.keyValues as x> 
    /**
     * ${x.index}:${x.comment}
     */
    ${x.code!enum.javaName?upper_case + "_" + x.index}("${x.index}", "${x.comment}")<#if x?has_next>,<#else>;</#if>
	</#list > 

	private String value;

	private String name;

	private static List<${enum.javaName}> list;

	${enum.javaName} (String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getDisplayNameByIndex(String value) {
		for (${enum.javaName?cap_first} enumOne : ${enum.javaName?cap_first}.values()) {
			if (value.equals(enumOne.getValue())) {
				return enumOne.getName();
			}
		}
		return "";
	}

	static {
	    list = new ArrayList<${enum.javaName?cap_first}>();
	    ${enum.javaName?cap_first}[] enumValues = ${enum.javaName?cap_first}.values();
	    for (${enum.javaName?cap_first} enumOne : enumValues) {
	        list.add(enumOne);
	    }
	}

	public String getValue() {
	    return value;
	}

	public void setValue(String value) {
	    this.value = value;
	}

	public String getName() {
        return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static List<${enum.javaName?cap_first}> getDataList() {
		return list;
	}
}
