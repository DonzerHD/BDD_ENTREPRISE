package Exercice9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

/**
 * Class qui va nous permettre d'aller chercher les informations dans la base de données .
 * @author thomas59
 *
 */
public class Entreprise {
        
	/**
	 * Méthode qui affiche la liste des employés 
	 * @param url
	 * @param login
	 * @param password
	 * @throws SQLTimeoutException
	 */
	public void afficheListeEmploye(String url, String login, String password) throws SQLTimeoutException {
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			String select = "SELECT nom,prenom,emploi FROM entreprise.emp";
			try (Statement statement = connection.createStatement()) {
				ResultSet resulSet = statement.executeQuery(select);
				while (resulSet.next()) {
					String nom = resulSet.getString(1);
					String prenom = resulSet.getString(2);
					String emploi = resulSet.getString(3);
       				StringBuilder Affichage = new StringBuilder();
					Affichage.append("nom : " + nom + " ");
					Affichage.append("prénom : " + prenom + " ");
					Affichage.append("emploi : " + emploi + " ");
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
