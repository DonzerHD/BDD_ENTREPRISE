package Exercice9PS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * class qui va permettre de changer des informations de l'employé dans la base de données
 * @author thomas59
 *
 */
public class ModifierEmploye {
           /**
         * Méthode qui va afficher le menu à l'utilisateur pour qu'il choisisse de modifier ce qu'il veut 
         */
        public void menu() {
        	System.out.println("----------------------------");
       		System.out.println("    |MODIFIER EMPLOYE|");
       		System.out.println("-----------------------------");
       		System.out.println("1 - noemp ");
       		System.out.println("2 - nom ");
       		System.out.println("3 - prenom ");
       		System.out.println("4 - emploi ");
       		System.out.println("5 - sup  ");
       		System.out.println("6 - date d'embauche  ");
       		System.out.println("7 - salaire : ");
       		System.out.println("8 - comm ");
       		System.out.println("9 - numéro service  ");
       		System.out.println("-------------------------");
       		System.out.print("Veuillez sélectionner une option : ");
           }
           
         /**
         * Méthode qui va permettre de changer le nom, prénom ou emploi d'un employé
         * @param nom
         * @param prenom
         * @param up
         * @return
         */
        public  String modifierNomPrenomEmploi(final String nom , final String prenom, final String up){
        	   Scanner sc = new Scanner(System.in);
        	   System.out.println("Modification " + up + " :" );
        	   String changeString = sc.nextLine();
        	   String change ="UPDATE entreprise.emp\n"
        	   		+ "SET " + up + " = '" + changeString + "' WHERE nom = '" + nom + "' AND prenom = '" + prenom + "';";
        	   return change;
        	   
           }
        
        /**
         * Méthode qui va permettre de changer le Noemp, Sup , Comm, Noserv d'un employé
         * @param nom
         * @param prenom
         * @param up
         * @return
         */
        public String modifierNoempSupCommNoserv(final String nom , final String prenom, final String up){
     	   Scanner sc = new Scanner(System.in);
     	   System.out.print("Modification " + up + " :" );
     	   int changeInt = sc.nextInt();
     	   String change ="UPDATE entreprise.emp\n"
     	   		+ "SET " + up + " = " + changeInt + " WHERE nom = '" + nom + "' AND prenom = '" + prenom + "';";
     	   return change;
     	   
        }
        
        /**
         * Méthode qui va permettre de modifier la date d'embauche d'un employé
         * @param nom
         * @param prenom
         * @param up
         * @return
         */
        public String modifierDateEmbauche(final String nom , final String prenom, final String up){
      	   Scanner sc = new Scanner(System.in);
      	   System.out.print("Modification " + up + " (date : AAAA-MM-JJ ) :" );
      	   String changeDate = sc.nextLine();
      	 if (changeDate.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
				LocalDate.parse(changeDate, DateTimeFormatter.ISO_DATE);
			} else {
				System.out.println("--------ERREUR---------------------------------------------");
				System.out.println("La date saisie est invalide");
				System.out.println("--------ERREUR--------------------------------------------");
			}
      	   String change ="UPDATE entreprise.emp\n"
      	   		+ "SET " + up + " = '" + changeDate + "' WHERE nom = '" + nom + "' AND prenom = '" + prenom + "';";
      	   return change;
      	   
         }
        
        /**
         * Méthode qui change le salaire d'un employé
         * @param nom
         * @param prenom
         * @param up
         * @return
         */
        public String modifierSalaire(final String nom , final String prenom, final String up){
      	   Scanner sc = new Scanner(System.in);
      	   System.out.print("Modification " + up + " :" );
      	   double changeDouble = sc.nextDouble();
      	   String change ="UPDATE entreprise.emp\n"
      	   		+ "SET " + up + " = " + changeDouble + " WHERE nom = '" + nom + "' AND prenom = '" + prenom + "';";
      	   return change;
      	   
         }
}
