package ${packageName}.dto;

<#if extendClass?? >
import java.io.Serializable;
</#if>
<#list table.importClass as x> 
<#if x??>
import ${x};
</#if>
</#list>
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：${table.comment} DTO类
 * 
 * @author ${author}
 * ${generateDateTime}
 */
<#if extendClass?? >
public class ${table.javaName}DTO implements Serializable {

    private static final long serialVersionUID = -1L;
<#else >
public class ${table.javaName}DTO extends ${extendClass?keep_after_last(".")} {
</#if>

    /** The Constant LOGGER */
    private static final Logger LOGGER = LoggerFactory.getLogger(${table.javaName}DTO.class);

    <#list columnList as x> 
    /** ${x.comment} */
    private ${x.javaType} ${x.javaName};
    
    </#list>

   	@Override
   	public String toString() {
        return "${table.javaName} [" +
        <#list columnList as x>
        <#if x?has_next>
        "${x.javaName}=" + ${x.javaName?uncap_first} + "," + 
        <#else>
        "${x.javaName}=" + ${x.javaName?uncap_first} +
        </#if>
        </#list>"]";
   	}

    <#list columnList as x>
    public ${x.javaType} get${x.javaName?cap_first}() {
        return this.${x.javaName};
    }

    public void set${x.javaName?cap_first}(${x.javaType} ${x.javaName}) {
        this.${x.javaName} = ${x.javaName};
    }
    
    </#list>
}