package ${packageName}.dto;

<#list table.importClass> 
<#items as x>
import ${x};
</#items>
</#list>

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

    <#list columnList as x> 
    /** ${x.comment} */
    private ${x.javaType} ${x.javaName};
    
    </#list>

    <#list columnList as x>
    public ${x.javaType} get${x.javaName}() {
        return this.${x.javaName};
    }

    public void set${x.javaName}(${x.javaType} ${x.javaName}) {
        this.${x.javaName} = ${x.javaName};
    }
    
    </#list>
}