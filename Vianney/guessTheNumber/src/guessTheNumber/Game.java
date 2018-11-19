package guessTheNumber;

import java.util.Random;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        int max = 255;
        int number = getRandomSecretNumber(max);
        int playerEntry = 0;
        int playerTries = 0;

        //Boucle qui s'execute tant que « playerEntry » != number
        while (playerEntry != number) {
            playerEntry = answerToPlayerAndRead(max);
            playerTries = helpPlayer(number, playerEntry, playerTries);
        }

        //Quand « playerEntry » = number la boucle se termine donc le joueur gagne
        congratPlayer(playerTries);
    }

    private static void congratPlayer(int playerTries) {
        if (playerTries <= 1) {
            System.out.println("Congrats, you found the right number the first time!!");
        } else {
            System.out.println("Congrats, you found the right number in " + playerTries + " tries!");
        }
    }

    private static int answerToPlayerAndRead(int max) {
        int playerEntry;
        //La console demande au joueurs d'entrer une valeur
        System.out.println("Enter a number between 0 and "+ max);
        //On initalise la fonction qui scane la console (valeur du joueur)
        Scanner scan = new Scanner(System.in);

        //On récupère la valeur donnée par le joueur
        playerEntry = scan.nextInt();
        return playerEntry;
    }

    private static int helpPlayer(int number, int playerEntry, int playerTries) {
        if (playerEntry != number) {
            if (playerEntry < number) {
                System.out.println("Try a bigger number");
            } else {
                System.out.println("Try a smaller number");
            }
            playerTries++;
        }
        return playerTries;
    }

    private static int getRandomSecretNumber(int max) {
        Random n = new Random();
        return n.nextInt(max);
    }
}
