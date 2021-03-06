package ${packageName}.${subPageName};

import ${packageName}.dto.${table.javaName}DTO;
import ${packageName}.entity.${table.javaName};
<#if extendClass?? >
<#else>
import ${extendClass};
</#if>
<#list table.importClass> 
<#items as x>
import ${x};
</#items>
</#list>
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * 描述：${table.javaName}Assembler Bean转换类
 * 
 * @author ${author}
 * ${generateDateTime}
 */
<#if extendClass?? >
public class ${table.javaName}Assembler extends ${extendClass?keep_after_last(".")} {
<#else>
public class ${table.javaName}Assembler {
</#if>

    /** 日志logger对象  */
    private static final Logger LOGGER = LoggerFactory.getLogger(${table.javaName}Assembler.class);
    
    /**
     * dto转换为entity
     *
     * @param ${table.javaName}Dto对象
     * @return ${table.javaName}对象
     */
    public static ${table.javaName} toEntity(${table.javaName}DTO ${table.javaName?uncap_first}Dto) {
    	${table.javaName} ${table.javaName?uncap_first} = new ${table.javaName}();
    	if (${table.javaName?uncap_first}Dto == null) {
    	   return ${table.javaName?uncap_first};
    	} else {
    	   <#if extendClass?? >
    	   ${extendClass?keep_after_last(".")}.mapObj${extendClass?keep_after_last("::")}(${table.javaName?uncap_first}Dto, ${table.javaName?uncap_first});
    	   <#else>
    	   BeanUtils.copyProperties(${table.javaName?uncap_first}Dto, ${table.javaName?uncap_first});
           </#if>
           return ${table.javaName?uncap_first};
	   	}
    }
    
    /**
     * dto集合转换为entity集合
     *
     * @param ${table.javaName}Dto对象集合
     * @return ${table.javaName}对象集合
     */
    public static List<${table.javaName}> toEntityList(List<${table.javaName}DTO> ${table.javaName?uncap_first}Dtos) {
    	${table.javaName} ${table.javaName?uncap_first} = new ${table.javaName}();
    	List<${table.javaName}> entityList = new ArrayList<${table.javaName}>();
    	if (CollectionUtils.isEmpty(${table.javaName?uncap_first}Dtos)) {
    	   return entityList;
    	} else {
    	   for (${table.javaName}DTO dtoOne: ${table.javaName?uncap_first}Dtos) {
    	       <#if extendClass?? >
               ${table.javaName?uncap_first} = toEntity${extendClass?keep_after_last("::")}(dtoOne);
               <#else>
               BeanUtils.copyProperties(dtoOne, ${table.javaName?uncap_first});
               </#if>
               entityList.add(${table.javaName?uncap_first});
            }
            return entityList;
	   	}
    }
	
	/**
     * entity转换为dto
     *
     * @param ${table.javaName}对象
     * @return ${table.javaName}Dto对象
     */
	public static ${table.javaName}DTO toDto(${table.javaName} ${table.javaName?uncap_first}) {
    	${table.javaName}DTO ${table.javaName?uncap_first}Dto = new ${table.javaName}DTO();
    	if (${table.javaName?uncap_first} == null) {
    	   return ${table.javaName?uncap_first}Dto;
    	} else {
    	   <#if extendClass?? >
           ${extendClass?keep_after_last(".")}.mapObj${extendClass?keep_after_last("::")}(${table.javaName?uncap_first}, ${table.javaName?uncap_first}Dto);
           <#else>
           BeanUtils.copyProperties(${table.javaName?uncap_first}, ${table.javaName?uncap_first}Dto);
           </#if>
           return ${table.javaName?uncap_first}Dto;
	   	}
    }
    
    /**
     * entity集合转换为dto集合
     *
     * @param ${table.javaName}对象集合
     * @return ${table.javaName}Dto对象集合
     */
    public static List<${table.javaName}DTO> toDtoList(List<${table.javaName}> ${table.javaName?uncap_first}s) {
    	${table.javaName}DTO ${table.javaName?uncap_first}Dto = new ${table.javaName}DTO();
    	List<${table.javaName}DTO> dtoList = new ArrayList<${table.javaName}DTO>();
    	if (CollectionUtils.isEmpty(${table.javaName?uncap_first}s)) {
    		return dtoList;
    	} else {
            for (${table.javaName} entityOne: ${table.javaName?uncap_first}s) {
                <#if extendClass?? >
                ${table.javaName?uncap_first}Dto = toDto(entityOne);
                <#else>
                BeanUtils.copyProperties(entityOne, ${table.javaName?uncap_first}Dto);
                </#if>
                dtoList.add(${table.javaName?uncap_first}Dto);
            }
            return dtoList;
	   	}
    }
}
