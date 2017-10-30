<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.tigerj.dao.${className}Mapper">
	<sql id="columns">
		<#list properties as prop>  
    		${prop.field} AS ${prop.name?uncap_first},
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
					${prop.field},
				</if>
			</#list>
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides=",">
			<#list properties as prop> 
				<if test="${prop.name?uncap_first} != null">
					#{prop.name?uncap_first},jdbcType=${prop.jdbcType},
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
					#{prop.field} ＝ #{prop.name?uncap_first},jdbcType=#{prop.jdbcType}},,
				</if>
			</#list>
			
		</set>
		where ID = <#macro mapperEl value>${r"#{"}${id}}</#macro>
		
	</update>

	<!-- 分页查询用户信息 -->
	<select id="findList" resultType="TestCustomer">
		SELECT
		<include refid="columns" />
		FROM
		<include refid="table_name" />
		<if test="firstName != null and firstName != ''">
			AND a.first_name like CONCAT('%', #{firstName}, '%')
		</if>
		<if test="lastName != null and lastName != ''">
			AND a.last_name like CONCAT('%', #{lastName}, '%')
		</if>
		<!-- 数据范围过滤 -->
		<!-- ${sqlMap.dsf} -->
		<choose>
			<when test="page !=null and page.orderBy != null and page.orderBy != ''">
				ORDER BY ${page.orderBy}
			</when>
			<otherwise>
				ORDER BY a.id
			</otherwise>
		</choose>
	</select>

</mapper>