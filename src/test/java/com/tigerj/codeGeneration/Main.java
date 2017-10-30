package com.tigerj.codeGeneration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.JdbcType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;

import com.tigerj.BaseTest;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class Main extends BaseTest{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	String tableName = "T_TEST_USER";
	String className = "TestCity";
	String entityName = "TestCity";
	Configuration cfg = null;
	String contextPath = System.getProperty("user.dir");
	String templatePath = contextPath+File.separator+"src/main/resources/codeGenerationTemplate"+File.separator;
	String javaCodePath = contextPath + "/src/main/java/com/tigerj/";
	String resourcePath = contextPath + "/src/main/resources/mapper/";

	Map<String, Object> root = null;
	List<String> excludeFieldsList = null;

	@Before
	public void before() throws IOException {

		cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(templatePath));
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		root = new HashMap<String, Object>();
		root.put("className", className);
		root.put("entityName", entityName);
		root.put("tableName", tableName);
		
		excludeFieldsList = new ArrayList<>();
		excludeFieldsList.add("id");
		excludeFieldsList.add("page");
		excludeFieldsList.add("sqlMap");
		excludeFieldsList.add("remarks");
		excludeFieldsList.add("createDate");
		excludeFieldsList.add("updateDate");
		excludeFieldsList.add("status");
	}

	@Test
	public void genCode() throws IOException, TemplateException {
		
//		genEntity();
		
		genXml();

//		genMapper();
//
//		genService();
//
//		genServiceImpl();
//
//		genController();

	}
	
	public void genXml() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		root.put("properties", queryFieldsByTableName(tableName)); 
		Template template = cfg.getTemplate("xml.ftl");
		Writer writer = new FileWriter(new File(resourcePath + entityName + "Mapper.xml"));
		template.process(root, writer);
	}
	
	public void genEntity() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		root.put("properties", queryAttrByTableName(tableName)); 
		Template template = cfg.getTemplate("entity.ftl");
		Writer writer = new FileWriter(new File(javaCodePath + "domain/" + entityName + ".java"));
		template.process(root, writer);
	}

	public void genController() throws IOException, TemplateException {

		Template template = cfg.getTemplate("controller.ftl");
		Writer writer = new FileWriter(new File(javaCodePath + "web/" + entityName + "Controller.java"));
		template.process(root, writer);
	}

	public void genMapper() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException,
			IOException, TemplateException {
		Template template = cfg.getTemplate("mapper.ftl");
		Writer writer = new FileWriter(new File(javaCodePath + "dao/" + entityName + "Mapper.java"));
		template.process(root, writer);

	}

	public void genService() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException,
			IOException, TemplateException {
		Template template = cfg.getTemplate("service.ftl");
		Writer writer = new FileWriter(new File(javaCodePath + "service/" + entityName + "Service.java"));
		template.process(root, writer);
	}

	public void genServiceImpl() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException,
			IOException, TemplateException {
		Template template = cfg.getTemplate("serviceImpl.ftl");
		Writer writer = new FileWriter(new File(javaCodePath + "service/impl/" + entityName + "ServiceImpl.java"));
		template.process(root, writer);
	}
	
	//@Test
	public void t(){
		queryFieldsByTableName("T_TEST_CUSTOMER");
	}
	
	/**
	 * 查询entity属性
	 */
	public Collection<Map<String, String>> queryAttrByTableName(String tableName){
		Collection<Map<String, String>> fieldsList = new HashSet<Map<String, String>>();  
		
		String sql = "select * from "+tableName;
		
		RowCountCallbackHandler rcch = new RowCountCallbackHandler();
		jdbcTemplate.query(sql,rcch);
		String[] coloumnName = rcch.getColumnNames();  
		int[] coloumnType = rcch.getColumnTypes();
		
		for(int i=0;i<coloumnName.length;i++){
			if (excludeFieldsList.contains(coloumnName[i])) {
				continue;
			}
			Map<String, String> tmpMap = new HashMap<>();
			tmpMap.put("field", coloumnName[i]);
			tmpMap.put("jdbcType", JdbcType.forCode(coloumnType[i]).name());
			tmpMap.put("name", camelName(coloumnName[i]));
			tmpMap.put("type", JavaType.forCode(coloumnType[i]).name());
			fieldsList.add(tmpMap);
		}
		
		return fieldsList;
	}
	
	/**
	 * 查询表字段
	 */
	public Collection<Map<String, String>> queryFieldsByTableName(String tableName){
		Collection<Map<String, String>> fieldsList = new HashSet<Map<String, String>>();  
		
		String sql = "select * from "+tableName;
		
		RowCountCallbackHandler rcch = new RowCountCallbackHandler();
		jdbcTemplate.query(sql,rcch);
		String[] coloumnName = rcch.getColumnNames();  
		int[] coloumnType = rcch.getColumnTypes();
		
		for(int i=0;i<coloumnName.length;i++){
			Map<String, String> tmpMap = new HashMap<>();
			tmpMap.put("field", coloumnName[i]);
			tmpMap.put("jdbcType", JdbcType.forCode(coloumnType[i]).name());
			tmpMap.put("name", camelName(coloumnName[i]));
			tmpMap.put("type", JavaType.forCode(coloumnType[i]).name());
			fieldsList.add(tmpMap);
		}
		
		return fieldsList;
	}
	
	
	public static String camelName(String name) {
		  StringBuilder result = new StringBuilder();
		  if (name == null || name.isEmpty()) {
		   return "";
		  } else if (!name.contains("_")) {
		   // 不含下划线，仅将首字母大写
		   return name.substring(0, 1).toUpperCase() + name.substring(1);
		  }
		  // 用下划线将原始字符串分割
		  String camels[] = name.split("_");
		  for (String camel : camels) {
		   // 跳过原始字符串中开头、结尾的下换线或双重下划线
		   if (camel.isEmpty()) {
		    continue;
		   }
		   result.append(camel.substring(0, 1).toUpperCase());
		   result.append(camel.substring(1).toLowerCase());
		  }
		  return result.toString();
		 }
	

}
