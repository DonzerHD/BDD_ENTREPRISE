package Exercice3a8PS;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * class ou dedans il y a les méthodes des exos 1 à 9
 * @author thomas59
 *
 */
public class ExoJDBC_MAIN {

	/**
	 * méthode qui affiche à l’écran la liste des employés du service 5
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLTimeoutException
	 */
	public void exo3(String url, String login, String password) throws SQLTimeoutException {
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
            String select = "SELECT * FROM entreprise.emp WHERE noserv = ?";
            
            try( PreparedStatement myStmt = connection.prepareStatement(select);){
                myStmt.setInt(1, 5);
             	ResultSet resulSet = myStmt.executeQuery();
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
			String select = "SELECT noemp,nom,prenom,emploi,sup,embauche,sal,comm,service  FROM entreprise.emp NATURAL JOIN entreprise.serv WHERE service = ?;";
			  try( PreparedStatement myStmt = connection.prepareStatement(select);){
	                myStmt.setString(1, "INFORMATIQUE");
	             	ResultSet resulSet = myStmt.executeQuery();
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
	
	/**
	 * méthode qui affiche à l’écran la liste des employés dont le nom est saisi par l’utilisateur
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLTimeoutException
	 */
	public void exo5(String url, String login, String password) throws SQLTimeoutException {
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Nom saisi par l’utilisateur : ");
			String nomSaisi = sc.nextLine();
			String select = "SELECT *  FROM entreprise.emp  WHERE nom = ?";
			
			  try( PreparedStatement myStmt = connection.prepareStatement(select);){
	                myStmt.setString(1, nomSaisi);
	             	ResultSet resulSet = myStmt.executeQuery();
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
					sc.close();
					connection.close();

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connexion non établie");
		}

	}
	/**
	 * méthode qui affiche à l’écran la liste des employés dont la date d’embauche est comprise dans l’année saisie par
       l’utilisateur
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLTimeoutException
	 * @throws ParseException 
	 */
	public void exo6(String url, String login, String password) throws SQLTimeoutException, ParseException {
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Année saisi par l’utilisateur : ");
			String annee = sc.nextLine();
			String anneeD = annee + "-01-01";
			String anneeE = annee + "-12-31";
			          
			String select = "SELECT *  FROM entreprise.emp WHERE embauche > ?  AND embauche < ?";
			  try( PreparedStatement myStmt = connection.prepareStatement(select);){
	                myStmt.setDate(1, Date.valueOf(anneeD));
	                myStmt.setDate(2, Date.valueOf(anneeE) );
	             	ResultSet resulSet = myStmt.executeQuery();
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
					sc.close();
					connection.close();

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connexion non établie");
		}

	}
	
	/**
	 * methode qui affiche à l’écran la liste des employés dont le nom contient une chaîne de caractères saisie par l’utilisateur
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLTimeoutException
	 */
	public void exo7(String url, String login, String password) throws SQLTimeoutException {
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Chaîne de caractères saisie par l’utilisateur : ");
			String chaine = sc.nextLine();
			String select = "SELECT *\n"
					+ "FROM entreprise.emp\n"
					+ "WHERE nom LIKE ?";
			
			try( PreparedStatement myStmt = connection.prepareStatement(select);){
                myStmt.setString(1,"%" + chaine + "%");
             	ResultSet resulSet = myStmt.executeQuery();
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
					sc.close();
					connection.close();

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connexion non établie");
		}

	}
	/**
	 * méthode qui affiche à l’écran les noms, emploi, salaire, numéro de service de tous les employés du service saisi par
       l’utilisateur et qui gagnent plus d’un salaire saisi par l’utilisateur
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLTimeoutException
	 */
	public void exo8(String url, String login, String password) throws SQLTimeoutException {
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Numéro service saisie par l’utilisateur : ");
			int numero = sc.nextInt();
			System.out.print("Salaire saisie par l’utilisateur : ");
			double salaire= sc.nextDouble();
			String select = "SELECT\n"
					+ "	nom,\n"
					+ "	emploi,\n"
					+ "	sal,\n"
					+ "	noserv\n"
					+ "FROM\n"
					+ "	entreprise.emp\n"
					+ "WHERE\n"
					+ "	sal > ? \n"
					+ "	AND noserv = ? " ;
			
			try( PreparedStatement myStmt = connection.prepareStatement(select);){
                myStmt.setInt(2, numero);
                myStmt.setDouble(1, salaire );
             	ResultSet resulSet = myStmt.executeQuery();
				while (resulSet.next()) {
					String nom = resulSet.getString(1);
					String emploi = resulSet.getString(2);
					double sal = resulSet.getDouble(3);			
					int numS = resulSet.getInt(4);
					StringBuilder Affichage = new StringBuilder();
					Affichage.append("nom : " + nom + " ");
					Affichage.append("emploi : " + emploi + " ");
					Affichage.append("salaire : " + sal + " euros ");
					Affichage.append("numéro service : " + numS);
					System.out.println(Affichage);
					sc.close();
					connection.close();

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connexion non établie");
		}

	}
}
