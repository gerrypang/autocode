package ${packageName}.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
<#list table.importClass as x> 
import ${x};
</#list>

/**
 * 描述：${table.comment} Entity类
 * 
 * @author ${author}
 * ${generateDateTime}
 */
@Entity
@Table(name = "${table.tableName}")
public class ${table.javaName} {

    private static final long serialVersionUID = -1L;
    
    <#list columnList as x>
    <#if x.cloumnKey == "pri" || x.cloumnKey == "PRI" > 
    /** 主键 */
    @id
    @GeneratedValue(generator = "${table.tableName}keyGen")
    @GenericGenerator(name = "${table.tableName}keyGen", strategy = "${x.extra}")
    @Column(name = "${x.columnName}", nullable = false, columnDefinition = "${x.columnTypeLength} comment '主键'")
    private ${x.javaType} ${x.javaName};
    </#if> 
    </#list>

    <#list columnList as x> 
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