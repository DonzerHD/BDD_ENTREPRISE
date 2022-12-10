package Exercice9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLTimeoutException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Class qui permet de lancer le programme avec le menu .
 * Pour le fichier PROPERTIES : Je mets les infos pour se connecter à la base de données  dans le fichier "properties" pour 
 * faire en sorte de changer le mot de passe ou nom d'utilisateur tous les mois pour plus de sécurité .
 * 
 * @author thomas59
 *
 */
public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLTimeoutException {

		// FICHIER PROPERTIES
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Properties props = new Properties();
		try (FileInputStream fis = new FileInputStream("conf.properties")) {
			props.load(fis);
		}

		Class.forName(props.getProperty("jdbc.driver.class"));
		final String url = props.getProperty("jdbc.url");
		final String login = props.getProperty("jdbc.login");
		final String password = props.getProperty("jdbc.password");
		
		//Création Entreprise
		Entreprise entreprise = new Entreprise();

		menu(); //MENU ET CHOIX DEBUT

		try {
			int choix = sc.nextInt();
			switch (choix) {

			case 1:
				entreprise.afficheListeEmploye(url, login, password);	
				retourMenu();
				break;
			case 2:
				entreprise.afficheDetailsEmploye(url, login, password);
				retourMenu();
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			default:
				System.out.println("Ne correspond à aucune option");
				main(null);
			}
		} catch (InputMismatchException e) {
			System.out.println("Rentrez un chiffre entre 1 et 5 !");
			main(null);
		}

	}

	/**
	 * Menu console ENTREPRISE choix
	 */
	public static void menu() {

		System.out.println("-------------------------");
		System.out.println("    |ENTREPRISE|");
		System.out.println("-------------------------");
		System.out.println("1 - Afficher la liste des employés");
		System.out.println("2 - Afficher le détail d'un employé");
		System.out.println("3 - Ajouter un employé ");
		System.out.println("4 - Modifier un employé à partir de son nom et prénom");
		System.out.println("5 - Supprimer un employé à partir de son nom et prénom");
		System.out.println("6 - Fermer le programme");
		System.out.println("-------------------------");
		System.out.print("Veuillez sélectionner une option : ");
	}
	
	/**
	 * Méthode qui va retourner au menu avec un temps d'attente
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * @throws SQLTimeoutException 
	 */
	public static void retourMenu() throws SQLTimeoutException, FileNotFoundException, ClassNotFoundException, IOException {
		try {
			System.out.println("----------------------------------------------");
			System.out.println("Retour dans le menu dans quelques secondes ...");
			System.out.println("----------------------------------------------");
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			System.out.println("erreur de chargement");
			main(null);
		}
		main(null);
	}

}
