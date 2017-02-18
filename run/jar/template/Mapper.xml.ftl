<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#if model??>
<mapper namespace="${model.packageName}.${model.model}<#if model.subModel??>.${model.subModel}</#if>.dao.${model.className}Dao">

    <sql id="${model.className}Columns">
<#list model.columns as column>
    <#if !column_has_next>
        a.${column.dbColumnName} as "${column.columnName}"
    <#else>
        a.${column.dbColumnName} as "${column.columnName}",
    </#if>
</#list>
    </sql>

    <sql id="${model.className}Joins">

    </sql>

    <select id="get" resultType="${model.packageName}.${model.model}<#if model.subModel??>.${model.subModel}</#if>.entity.${model.className}">
        SELECT
        <include refid="${model.className}Columns"/>
        FROM ${model.tableName} a
        WHERE a.id = ${"#"}{id}
    </select>

    <select id="findList" resultType="${model.packageName}.${model.model}<#if model.subModel??>.${model.subModel}</#if>.entity.${model.className}">
        SELECT
        <include refid="${model.className}Columns"/>
        FROM ${model.tableName} a
    </select>

    <select id="count" resultType="int">
        SELECT count(1) FROM ${model.tableName} a
        <where>

        </where>
    </select>

    <select id="findPage" resultType="${model.packageName}.${model.model}<#if model.subModel??>.${model.subModel}</#if>.entity.${model.className}">
        SELECT <include refid="${model.className}Columns"/> FROM ${model.tableName} a
        <where>

        </where>
        <if test="page.orderBy!=''">
            ORDER BY ${"$"}{page.orderBy} LIMIT ${"$"}{page.pageNo*page.pageSize},${"$"}{page.pageSize}
        </if>
    </select>

    <insert id="insert">
        insert into ${model.tableName}(
        <#list model.columns as column>
            <#if column.columnName=="id">
            <#else>
                <#if !column_has_next>
                ${column.dbColumnName}
                <#else>
                ${column.dbColumnName},
                </#if>
            </#if>
        </#list>
        )
        values(
        <#list model.columns as column>
            <#if column.columnName=="id">
            <#else>
                <#if !column_has_next>
                ${"#"}{${column.columnName}}
                <#else>
                ${"#"}{${column.columnName}},
                </#if>
            </#if>
        </#list>
        )
    </insert>

    <update id="update">
        update ${model.tableName} set
        <#list model.columns as column>
            <#if column.columnName=="id">
            <#else>
                <#if !column_has_next>
                ${column.dbColumnName}=${"#"}{${column.columnName}}
                <#else>
                ${column.dbColumnName}=${"#"}{${column.columnName}},
                </#if>
            </#if>
        </#list>
        where id=${"#"}{id}
    </update>

    <delete id="delete">
        delete from ${model.tableName} where id=${"#"}{id}
    </delete>
</mapper>
</#if>