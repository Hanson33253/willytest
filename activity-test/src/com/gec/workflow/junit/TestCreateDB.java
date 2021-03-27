package com.gec.workflow.junit;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class TestCreateDB {

	
	@Test
public void textJdbc(){
	  //
		ProcessEngineConfiguration config=ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		config.setJdbcDriver("com.mysql.jdbc.Driver");                  
		config.setJdbcUrl("jdbc:mysql://localhost:3306/activitydb2021?useUnicode=true&characterEncoding=utf-8&nullCatalogMeansCurrent=true&userSSL=false&serverTimezone=GMT%2B8");
	                       
	                       config.setJdbcUsername("root");
		                   config.setJdbcPassword("root");
		 config.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		 
	ProcessEngine processEngine=config.buildProcessEngine();
	System.out.println(processEngine);
	

	
	
}
}
