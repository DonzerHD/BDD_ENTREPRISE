package com.bdd.jdbc;

import java.io.FileInputStream;
import java.util.Properties;


public class Exo1 {
	public static void main(String[] args) throws Exception{
	      Properties props = new Properties();
	      try (FileInputStream fis = new FileInputStream("conf.properties")){
	    	  props.load(fis);
	      }
	      
			 Class.forName(props.getProperty("jdbc.driver.class"));
			 
			 String url = props.getProperty("jdbc.url");
			 String login = props.getProperty("jdbc.login");
			 String password = props.getProperty("jdbc.password");
			 
		     ExoJDBC Exo = new ExoJDBC();
		     
		     
			 
		}
}
