package com.bdd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

public class ExoJDBC {

	/**
	 * méthode qui connecte la base de données à Java
	 * @param url 
	 * @param login
	 * @param password
	 * @throws SQLException
	 */
	public void exo1(String url, String login, String password) throws SQLTimeoutException{
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
            System.out.println("Connexion établie");
		}catch (SQLException  e) {
			System.out.println("Connexion non établie");
		}

	}
	public void exo2(String url, String login, String password) {
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
            String select = "SELECT * FROM emp";
     
		}catch (SQLException  e) {
			System.out.println("Connexion non établie");
		}
		
	}
}
