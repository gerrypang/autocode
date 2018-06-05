package ${packageName}.enum;

import java.util.ArrayList;
import java.util.List;
${!importStr}

public class ${className}Enum {

    /**
     * 1001:全部通道广播消息
     */
    DATA_TARGET_TO_ALL("1001", "全部通道"),
    
    /**
     * 1002:Alix直连消息
     */
    DATE_TARGET_TO_ALIX("1002", "ALix");

	private String value;

	private String name;

	private static List<${className}Enum> list;

	${className}Enum(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getDisplayNameByIndex(String value) {
		for (${className}Enum enum : ${className}Enum.values()) {
			if (value.equals(enum.getValue())) {
				return enum.getName();
			}
		}
		return "";
	}

	static {
		list = new ArrayList<${className}Enum>();
		${className}Enum[] enumValues = ${className}Enum.values();
		for (${className}Enum enum : enumValues) {
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

	public static List<${className}Enum> getDataList() {
		return list;
	}
}