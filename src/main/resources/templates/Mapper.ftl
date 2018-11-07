<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${packageName}.mapper.${table.javaName}Mapper">
    <resultMap id="${table.javaName}DTOResultMap" type="${packageName}.dto.${table.javaName}DTO">
    	<#list columnList as x> 
        <result column="${x.columnName}" jdbcType="${x.columnType?upper_case}" property="${x.javaName}" />
        </#list>
    </resultMap>
    
    <sql id="">
		
	</sql>
    
    <select id="" resultMap="">
    	select 
    </select>

</mapper>