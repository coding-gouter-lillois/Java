package guessTheNumber;

import java.util.Random;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		
		//Création d'un nombre aléatoire
		Random n = new Random();
		int number = n.nextInt(255);
		int playerTry = 0;
		int playerTries = 0;
		
		/*System.out.println("Number is : " + number);*/
		//Boucle qui s'execute tant que « playerTry » != number
		while(playerTry != number){
			
			//La console demande au joueurs d'entrer une valeur
			System.out.println("Enter a number between 0 and 255");
			//On initalise la fonction qui scane la console (valeur du joueur)
			Scanner scan = new Scanner(System.in);
			
			//On récupère la veleur donnée par le joueur
			playerTry = scan.nextInt();
			
			//Aide pour le joueur
				if(playerTry != number){
					if(playerTry < number){
						System.out.println("Try a bigger number");
						
					} else if(playerTry > number) {
						System.out.println("Try a smaller number");
						
					}
					
					playerTries ++;
				}
				
			
		}
		
		//Quand « playerTry » = number la boucle se termine donc le joueur gagne
		if(playerTries <= 1){
			System.out.println("Congrats, you found the right number in 1 try!!!");
			
		} else {
			System.out.println("Congrats, you found the right number in " + playerTries + " tries!");
		}
	}

}
