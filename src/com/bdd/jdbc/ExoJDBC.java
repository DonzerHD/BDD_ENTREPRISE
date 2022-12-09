package com.bdd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExoJDBC {

	/**
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLException
	 */
	public void exo1(String url, String login, String password) throws SQLException {
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
            System.out.println("Connexion établie");
		}catch (Exception e) {
			System.out.println("Connexion non établie");
		}

	}
}
