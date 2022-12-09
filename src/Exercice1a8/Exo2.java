package Exercice1a8;

import java.io.FileInputStream;
import java.util.Properties;

public class Exo2 {
	public static void main(String[] args) throws Exception{
	      Properties props = new Properties();
	      try (FileInputStream fis = new FileInputStream("conf.properties")){
	    	  props.load(fis);
	      }
	      
			 Class.forName(props.getProperty("jdbc.driver.class"));
			 
			 String url = props.getProperty("jdbc.url");
			 String login = props.getProperty("jdbc.login");
			 String password = props.getProperty("jdbc.password");
			 
		     ExoJDBC_MAIN Exo = new ExoJDBC_MAIN();
		     Exo.exo2(url, login, password);
		     
		     
			 
		}

}
