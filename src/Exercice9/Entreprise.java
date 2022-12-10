package Exercice9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Class qui va nous permettre d'aller chercher les informations dans la base de
 * données .
 * 
 * @author thomas59
 *
 */
public class Entreprise {

	/**
	 * Méthode qui affiche la liste des employés
	 * 
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLTimeoutException
	 */
	public void afficheListeEmploye(final String url, final String login, final String password)
			throws SQLTimeoutException {
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			String select = "SELECT nom,prenom,emploi FROM entreprise.emp";
			try (Statement statement = connection.createStatement()) {
				ResultSet resulSet = statement.executeQuery(select);
				System.out.println("----------------------------------------------");
				System.out.println("Affichage de la liste des employés : ");
				System.out.println("----------------------------------------------");
				while (resulSet.next()) {
					String nom = resulSet.getString(1);
					String prenom = resulSet.getString(2);
					String emploi = resulSet.getString(3);
					StringBuilder Affichage = new StringBuilder();
					Affichage.append("Nom : " + nom + " | ");
					Affichage.append("Prénom : " + prenom + " | ");
					Affichage.append("Emploi : " + emploi + " | ");
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
	 * méthode qui affiche le détail d’un employé .
	 * 
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLTimeoutException
	 */
	public void afficheDetailsEmploye(final String url, final String login, final String password)
			throws SQLTimeoutException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean donneesExiste = false;
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			System.out.print("Saisissez le nom de l'employé : ");
			String nomSaisie = sc.nextLine();
			System.out.print("Saisissez le prénom de l'employé : ");
			String prenomSaisie = sc.nextLine();
			String select = "SELECT * FROM entreprise.emp WHERE nom = UPPER('" + nomSaisie + "') AND prenom = UPPER('"
					+ prenomSaisie + "')";
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
					
					if (nomSaisie.equalsIgnoreCase(nom) && prenomSaisie.equalsIgnoreCase(prenom)) {
						donneesExiste = true;
						StringBuilder Affichage = new StringBuilder();
						System.out.println("----------------------------------------------");
						System.out.println("Voici le détail de l'employé : ");
						System.out.println("----------------------------------------------");
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
					}            
				}
				if ( donneesExiste == false) {
					       System.out.println("----------ERREUR------------------------------");
	                       System.out.println(" Ce prénom ou nom ne correspond à aucun employé.");
	                       System.out.println("----------ERREUR------------------------------");
						}
			}

		} catch (SQLException e) {
			System.out.println("Connexion non établie");
		}

	}
}
