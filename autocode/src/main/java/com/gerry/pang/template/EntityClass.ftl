package ${classPath};

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
${importStr}

@Entity
@Table(name = "${tableName}")
public class ${className}Entity {

    private static final long serialVersionUID = -1L;

    @Column(name = "AID", nullable = false, columnDefinition = "varchar(32) comment '主键'")
    private String aId;

}