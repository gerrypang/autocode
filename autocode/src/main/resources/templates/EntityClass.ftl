package ${packageName}.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
<#list table.importClass> 
<#items as x>
import ${x};
</#items>
</#list>

/**
 * 描述：${table.comment} Entity类
 * 
 * @author ${author}
 * ${generateDateTime}
 */
@Entity
@Table(name = "${table.tableName}")
<#if extendClass?? >
public class ${table.javaName} extends ${extendClass?keep_after_last(".")} {
<#else>
public class ${table.javaName} implements Serializable {
</#if>

    private static final long serialVersionUID = -1L;

    <#list columnList as x> 
    <#if x.cloumnKey == "pri" || x.cloumnKey == "PRI" > 
    /** 主键 */
    @id
    @GeneratedValue(generator = "${table.javaName?uncap_first}KeyGenerate")
    @GenericGenerator(name = "${table.javaName?uncap_first}KeyGenerate", strategy = "${x.extra}")
    @Column(name = "${x.columnName}", nullable = false, columnDefinition = "${x.columnTypeLength} comment '主键'")
    private ${x.javaType} ${x.javaName};
    
    </#if> 
    <#if x.cloumnKey != "pri" || x.cloumnKey != "PRI" > 
    /** ${x.comment} */
    @Column(name = "${x.columnName}", nullable = ${x.nullable?string('true', 'false')}, columnDefinition = "${x.columnTypeLength} comment '${x.comment}'")
    private ${x.javaType} ${x.javaName};
    
    </#if> 
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