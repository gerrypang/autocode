package ${packageName}.entity;

<#if extendClass?? >
import java.io.Serializable;
</#if>
<#if table.importClass?exists>
<#list table.importClass as x> 
import ${x};
</#list>
</#if> 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：${table.comment} Entity类
 * 
 * @author ${author}
 * ${generateDateTime}
 */
@Entity
@Table(name = "${table.tableName}")
<#if extendClass?? >
public class ${table.javaName} implements Serializable {

    private static final long serialVersionUID = -1L;
<#else>
public class ${table.javaName} extends ${extendClass?keep_after_last(".")} {
</#if>

    /** 日志logger对象  */
    private static final Logger LOGGER = LoggerFactory.getLogger(${table.javaName}.class);

    <#list columnList as x> 
    <#if x.cloumnKey == "pri" || x.cloumnKey == "PRI" > 
    /** 主键 */
    @Id
    @GeneratedValue(generator = "${table.javaName?uncap_first}KeyGenerate")
    @GenericGenerator(name = "${table.javaName?uncap_first}KeyGenerate", strategy = "${x.extra}")
    @Column(name = "${x.columnName}", nullable = false, columnDefinition = "${x.columnTypeLength} comment '主键'")
    private ${x.javaType} ${x.javaName};
    
    <#elseif x.cloumnKey == "" > 
    /** ${x.comment!x.javaName} */
    @Column(name = "${x.columnName}", nullable = ${x.nullable?string('true', 'false')}, columnDefinition = "${x.columnTypeLength} comment '${x.comment}'")
    private ${x.javaType} ${x.javaName};
    
    </#if>
    </#list>
    
   	@Override
   	public String toString() {
        return "${table.javaName} [" +
        <#list columnList as x>
        <#if x?has_next>
        "${x.javaName}=" + ${x.javaName?uncap_first} + "," + 
        <#else>
        "${x.javaName}=" + ${x.javaName?uncap_first} + "]"
        </#if>
        </#list>;
   	}
   	
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
            if (other.${x.javaName?uncap_first} != null) { return false; }
        } else if (!${x.javaName?uncap_first}.equals(other.${x.javaName?uncap_first})) {
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
