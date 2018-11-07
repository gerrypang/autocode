package ${packageName}.${subPageName};

<#if extendClass?? >
import java.io.Serializable;
import ${packageName}.entity.${table.javaName};
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
public class ${table.javaName}DTO extends ${extendClass?keep_after_last(".")} {
<#else >
public class ${table.javaName}DTO implements Serializable {
</#if>

    private static final long serialVersionUID = -1L;

    /** 日志logger对象  */
    private static final Logger LOGGER = LoggerFactory.getLogger(${table.javaName}DTO.class);

    <#list columnList as x> 
    /** ${x.comment!x.javaName} */
    private ${x.javaType} ${x.javaName};
    
    </#list>

   	@Override
    public String toString() {
        return "${table.javaName} [" +
        <#list columnList as x>
        "${x.javaName}=" + ${x.javaName?uncap_first}<#if x?has_next> + "," + <#else> + "]"</#if>
        </#list>;
    }
   	    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        <#list columnList as x>
        result = prime * result + ((${x.javaName} == null) ? 0 : ${x.javaName}.hashCode());
        </#list>
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        ${table.javaName} other = (${table.javaName}) obj;
        <#list columnList as x>
        if (${x.javaName} == null) {
            if (other.get${x.javaName?cap_first}() != null) { return false; }
        } else if (!${x.javaName?uncap_first}.equals(other.get${x.javaName?cap_first}())) {
            return false;
        }
        </#list>
        return true;
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