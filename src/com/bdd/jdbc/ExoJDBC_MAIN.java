package com.bdd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

public class ExoJDBC_MAIN {

	/**
	 * méthode qui connecte la base de données à Java
	 * 
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLException
	 */
	public void exo1(String url, String login, String password) throws SQLTimeoutException {
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			System.out.println("Connexion établie");
		} catch (SQLException e) {
			System.out.println("Connexion non établie");
		}

	}

	/**
	 * méthode qui affiche à l’écran la liste des employés
	 * 
	 * @param url
	 * @param login
	 * @param password
	 */
	public void exo2(String url, String login, String password) throws SQLTimeoutException {
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			String select = "SELECT * FROM entreprise.emp";
			try (Statement statement = connection.createStatement()) {
				ResultSet resulSet = statement.executeQuery(select);
				while (resulSet.next()) {
					int noemp = resulSet.getInt(1);
					String nom = resulSet.getString(2);
					String prenom = resulSet.getString(3);
					String emploi = resulSet.getString(4);
					int sup = resulSet.getInt(5);
					String embauche = resulSet.getString(6);
					double sal = resulSet.getDouble(7);
					int comm = resulSet.getInt(8);
					int numS = resulSet.getInt(9);

					StringBuilder Affichage = new StringBuilder();
					Affichage.append("no emp : " + noemp + " ");
					Affichage.append("nom : " + nom + " ");
					Affichage.append("prénom : " + prenom + " ");
					Affichage.append("emploi : " + emploi + " ");
					Affichage.append("sup : " + sup + " ");
					Affichage.append("date embauche : " + embauche + " ");
					Affichage.append("salaire : " + sal + " euros ");
					Affichage.append("commission : " + comm + " ");
					Affichage.append("numéro service : " + numS);
					System.out.println(Affichage);
					connection.close();

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connexion non établie");
		}

	}

	/**
	 * méthode qui affiche à l’écran la liste des employés du service 5
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLTimeoutException
	 */
	public void exo3(String url, String login, String password) throws SQLTimeoutException {
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
            String select = "SELECT * FROM entreprise.emp WHERE noserv = 5";
            try(Statement statement = connection.createStatement()){
             	ResultSet resulSet = statement.executeQuery(select);
            	 while(resulSet.next()) {
            		 int noemp 	= resulSet.getInt(1);
            		 String nom = resulSet.getString(2);
            		 String prenom = resulSet.getString(3);
            		 String emploi = resulSet.getString(4);
            		 int sup = resulSet.getInt(5);
            		 String embauche = resulSet.getString(6);
            		 double sal = resulSet.getDouble(7);
            		 int comm = resulSet.getInt(8);
            		 int numS = resulSet.getInt(9);
            		 
            		 StringBuilder Affichage = new StringBuilder();
            		 Affichage.append("no emp : " + noemp + " ");
            		 Affichage.append("nom : " + nom + " ");
            		 Affichage.append("prénom : " + prenom + " ");
            		 Affichage.append("emploi : " + emploi + " ");
            		 Affichage.append("sup : " + sup + " ");
            		 Affichage.append("date embauche : " + embauche + " ");
            		 Affichage.append("salaire : " + sal + " euros ");
            		 Affichage.append("commission : " + comm + " ");
            		 Affichage.append("numéro service : " + numS);
            		 System.out.println(Affichage);
            		 connection.close();
            		 
            		 
            	 }
            }
     
		}catch (SQLException  e) {
			e.printStackTrace();
			System.out.println("Connexion non établie");
		}
	}

	/**
	 * Méthode qui affiche à l’écran la liste des employés du service INFORMATIQUE
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLTimeoutException
	 */
	public void exo4(String url, String login, String password) throws SQLTimeoutException {
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			String select = "SELECT noemp,nom,prenom,emploi,sup,embauche,sal,comm,service  FROM entreprise.emp NATURAL JOIN entreprise.serv WHERE service = 'INFORMATIQUE';";
			try (Statement statement = connection.createStatement()) {
				ResultSet resulSet = statement.executeQuery(select);
				while (resulSet.next()) {
					int noemp = resulSet.getInt(1);
					String nom = resulSet.getString(2);
					String prenom = resulSet.getString(3);
					String emploi = resulSet.getString(4);
					int sup = resulSet.getInt(5);
					String embauche = resulSet.getString(6);
					double sal = resulSet.getDouble(7);
					int comm = resulSet.getInt(8);
					String service = resulSet.getString(9);

					StringBuilder Affichage = new StringBuilder();
					Affichage.append("no emp : " + noemp + " ");
					Affichage.append("nom : " + nom + " ");
					Affichage.append("prénom : " + prenom + " ");
					Affichage.append("emploi : " + emploi + " ");
					Affichage.append("sup : " + sup + " ");
					Affichage.append("date embauche : " + embauche + " ");
					Affichage.append("salaire : " + sal + " euros ");
					Affichage.append("commission : " + comm + " ");
					Affichage.append("Service : " + service);
					System.out.println(Affichage);
					connection.close();

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connexion non établie");
		}

	}
}
