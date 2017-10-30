package com.tigerj.domain;

import javax.persistence.Table;

import com.tigerj.dao.common.DataEntity;

import lombok.Data;

@Table(name="${tableName}")
@Data
public class ${className} extends DataEntity<${entityName}> {
	
	private static final long serialVersionUID = 1L;
    
    <#list properties as prop>  
    private ${prop.type?cap_first} ${prop.name?uncap_first};  
  </#list>  
}
