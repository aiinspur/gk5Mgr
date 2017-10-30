<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.tigerj.dao.${className}Mapper">
	<sql id="columns">
	<#list properties as prop>
		${prop.field} AS ${prop.name?uncap_first}<#if prop_has_next>,</#if>
	</#list>  
	</sql>

	<sql id="table_name">${tableName}</sql>

	<select id="selectById" parameterType="java.lang.Long"
		resultType="${entityName}">
		select
		<include refid="columns" />
		from
		<include refid="table_name" />
		where id = ${r'#{id}'}
	</select>

	<insert id="insert" parameterType="${entityName}">
		insert into
		<include refid="table_name" />
		<trim prefix="(" suffix=")" suffixOverrides=",">
		<#list properties as prop> 
			<if test="${prop.name?uncap_first} != null">
				${prop.field}<#if prop_has_next>,</#if>
			</if>
		</#list>
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides=",">
		<#list properties as prop> 
			<if test="${prop.name?uncap_first} != null">
				${r'#{'}${prop.name?uncap_first},jdbcType=${prop.jdbcType}${r'}'}<#if prop_has_next>,</#if>
			</if>
		</#list>			
		</trim>
	</insert>

	<update id="update">
		update
		<include refid="table_name" />
		<set>
		<#list properties as prop> 
			<if test="${prop.name?uncap_first} != null">
				${prop.field} ＝ ${r'#{'}${prop.name?uncap_first},jdbcType=${prop.jdbcType}${r'}'}<#if prop_has_next>,</#if>
			</if>
		</#list>
		</set>
		where ID = ${r'#{id}'}
	</update>
</mapper>