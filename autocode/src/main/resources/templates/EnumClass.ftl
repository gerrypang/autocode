package ${packageName}.enum;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：${table.comment} Enum类
 * 
 * @author ${author}
 * ${generateDateTime}
 */
public class ${javaName}Enum {

	<#list columnList as x> 
    /**
     * ${x.codeValue}:${x.codeName}
     */
    ${x.codeName}("${x.codeValue}", "${x.codeName}")<#if x?has_next>,<#else>;</#if>
	<#list columnList as x> 

	private String value;

	private String name;

	private static List<${javaName}Enum> list;

	${javaName}Enum(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getDisplayNameByIndex(String value) {
		for (${javaName}Enum enum : ${javaName}Enum.values()) {
			if (value.equals(enum.getValue())) {
				return enum.getName();
			}
		}
		return "";
	}

	static {
		list = new ArrayList<${javaName}Enum>();
		${javaName}Enum[] enumValues = ${javaName}Enum.values();
		for (${javaName}Enum enum : enumValues) {
			list.add(enum);
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

	public static List<${javaName}Enum> getDataList() {
		return list;
	}
}