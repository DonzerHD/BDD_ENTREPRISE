package Exercice9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.postgresql.util.PSQLException;

/**
 * Class qui va nous permettre d'aller chercher les informations dans la base de
 * données et les modifiées
 * 
 * @author thomas59
 *
 */
public class Entreprise extends Main {

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
			System.out.println("Connexion non établie");
		}

	}

	/**
	 * méthode qui affiche le détail d’un employé par une recherche . L'utilisateur
	 * devra saisir un nom et un prénom .
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
				if (donneesExiste == false) {
					System.out.println("----------ERREUR------------------------------");
					System.out.println(" Ce prénom ou nom ne correspond à aucun employé.");
					System.out.println("----------ERREUR------------------------------");
				}
			}

		} catch (SQLException e) {
			System.out.println("Connexion non établie");
		}
	}

	/**
	 * méthode qui va permettre d'ajouter un employé . L'utilisateur saisira les
	 * informations.
	 * 
	 * @param url
	 * @param login
	 * @param password
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	public void ajouterUnEmploye(final String url, final String login, final String password)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			System.out.println("---------------------------------------------------------");
			System.out.println("Rentrez les informations de l'employé qui sera ajouté. : ");
			System.out.println("---------------------------------------------------------");
			System.out.print("NoEmp : ");
			int noemp = sc.nextInt();
			sc.nextLine();
			System.out.print("Nom : ");
			String nom = sc.nextLine();
			System.out.print("Prénom : ");
			String prenom = sc.nextLine();
			System.out.print("Emploi : ");
			String emploi = sc.nextLine();
			System.out.print("Sup : ");
			int sup = sc.nextInt();
			sc.nextLine();
			System.out.print("Embauche (date : AAAA-MM-JJ ) : ");
			String date = sc.nextLine();
			if (date.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
				LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
			} else {
				System.out.println("--------ERREUR---------------------------------------------");
				System.out.println("La date saisie est invalide");
				System.out.println("--------ERREUR--------------------------------------------");
				retourMenu();
				main(null);
			}
			System.out.print("Salaire : ");
			double sal = sc.nextDouble();
			System.out.print("Comm : ");
			int com = sc.nextInt();
			System.out.print("Service numéro : ");
			int noserv = sc.nextInt();
			String insert = "INSERT\n" + "	INTO\n" + "	entreprise.emp (noemp,\n" + "	nom,\n" + "	prenom,\n"
					+ "	emploi,\n" + "	sup,\n" + "	embauche,\n" + "	sal,\n" + "	comm,\n" + "	noserv )\n"
					+ "VALUES ( " + noemp + ",\n" + "'" + nom + "',\n" + "'" + prenom + "',\n" + "'" + emploi + "',\n"
					+ sup + ",\n" + "'" + date + "',\n" + sal + ",\n" + com + ",\n" + noserv + " )";
			try (Statement statement = connection.createStatement()) {
				statement.executeUpdate(insert);
				System.out.println("---------------------------------------------------------");
				System.out.println("Employé à bien été ajouter dans la base de données");
				System.out.println("---------------------------------------------------------");
				connection.close();

			}

		} catch (PSQLException e1) {
			System.out.println("----------------ERREUR------------------------------------------");
			System.out.println(" Un ou plusieurs champs en dehors des limites ou emp déjà existant !");
			System.out.println("----------------ERREUR------------------------------------------");
		} catch (SQLException e2) {
			System.out.println("erreur2");
		} catch (InputMismatchException e) {
			System.out.println("--------------ERREUR------------------------------------------");
			System.out.println("         Saisie invalide!");
			System.out.println("--------------ERREUR------------------------------------------");
		} catch (DateTimeException e) {
			System.out.println("--------ERREUR---------------------------------------------");
			System.out.println("La date saisie est invalide");
			System.out.println("--------ERREUR--------------------------------------------");
		}

	}

	public void supprimerUnEmploye(final String url, final String login, final String password)
			throws SQLTimeoutException {
		 Scanner sc = new Scanner(System.in);
		 boolean donneesExiste = false;
         System.out.println("Le nom de l'employé que vous voulez supprimer : ");
         String nom = sc.nextLine();
         System.out.println("Le prénom de l'employé que vous voulez supprimer : ");
         String prenom = sc.nextLine();
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			String delete = "DELETE FROM entreprise.emp WHERE nom = '" + nom  + "' AND prenom = '" + prenom + "';";
			String select = "SELECT nom,prenom FROM entreprise.emp WHERE nom = UPPER('" + nom + "') AND prenom = UPPER('" + prenom + "')";
			try (Statement statement = connection.createStatement()) {
				ResultSet resulSet = statement.executeQuery(select);
				while (resulSet.next()) {
					String nomR = resulSet.getString(1);
					String prenomR = resulSet.getString(2);
					if (nomR.equalsIgnoreCase(nom) && prenomR.equalsIgnoreCase(prenom)) {
						donneesExiste = true;
						statement.executeUpdate(delete);
						System.out.println("L'employé a bien été supprimé");
						}
				} if(donneesExiste == false){
					System.out.println("----------ERREUR------------------------------");
					System.out.println(" Ce prénom ou nom ne correspond à aucun employé.");
					System.out.println("----------ERREUR------------------------------");
				}
				

			connection.close();

			}
		} catch (SQLException e1) {
			System.out.println("Connexion non établie");
		}

	}

}
