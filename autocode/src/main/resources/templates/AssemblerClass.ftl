package ${packageName}.assembler;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
<#list table.importClass> 
<#items as x>
import ${x};
</#items>
</#list>

/**
 * 描述：${className}Assembler Bean转换类
 * 
 * @author ${author}
 * ${generateDateTime}
 */
<#if extendClass?? >
public class ${table.javaName}Assembler extends ${extendClass?keep_after_last(".")} {
<#else>
public class ${table.javaName}Assembler {
</#if>

    /** The Constant LOGGER */
    private static final Logger LOGGER = LoggerFactory.getLogger(${table.javaName}Assembler.class);
    
    public ${table.javaName} toEntity(${table.javaName}DTO ${table.javaName}Dto) {
    	${table.javaName} ${table.javaName?uncap_first} = null;
    	if(${table.javaName}Dto == null) {
    		return ${table.javaName?uncap_first};
    	} else {
    		
	   	}
    }
    
    public List<${table.javaName}> toEntityList(List<${table.javaName}DTO> ${table.javaName}Dtos) {
    	${table.javaName} ${table.javaName?uncap_first} = null;
    	if(CollectionUtils.isEmpty(${table.javaName}Dtos)) {
    		return ${table.javaName?uncap_first};
    	} else {
    		
	   	}
    }
	
	
	public ${table.javaName}DTO toDto(${table.javaName} ${table.javaName}) {
    	${table.javaName}DTO ${table.javaName?uncap_first} = null;
    	if(${table.javaName}Dto == null) {
    		return ${table.javaName?uncap_first};
    	} else {
    		
	   	}
    }
    
    public List<${table.javaName}DTO> toDtoList(List<${table.javaName}> ${table.javaName}) {
    	${table.javaName}DTO ${table.javaName?uncap_first}Dto = null;
    	if(CollectionUtils.isEmpty(${table.javaName}Dtos)) {
    		return ${table.javaName?uncap_first};
    	} else {
    		
	   	}
    }

}