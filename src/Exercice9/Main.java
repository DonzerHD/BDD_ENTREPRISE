package Exercice9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLTimeoutException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import Exercice1a8.ExoJDBC_MAIN;

/**
 * Class qui permet de lancer le programme avec le menu .
 * 
 * @author thomas59
 *
 */
public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLTimeoutException {

		// FICHIER PROPERTIES
		Scanner sc = new Scanner(System.in);
		Properties props = new Properties();
		try (FileInputStream fis = new FileInputStream("conf.properties")) {
			props.load(fis);
		}

		Class.forName(props.getProperty("jdbc.driver.class"));
		String url = props.getProperty("jdbc.url");
		String login = props.getProperty("jdbc.login");
		String password = props.getProperty("jdbc.password");
		
		//Création Entreprise
		Entreprise entreprise = new Entreprise();

		menu(); //MENU ET CHOIX DEBUT

		try {
			int choix = sc.nextInt();
			switch (choix) {

			case 1:
				entreprise.afficheListeEmploye(url, login, password);
				
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
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
		System.out.println("-------------------------");
		System.out.print("Veuillez sélectionner une option : ");
	}

}
