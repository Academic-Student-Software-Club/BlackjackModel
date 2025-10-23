package com.assc.blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Model {
    
    public static void main(String[] args) {
        run();
    }
    
    public static void run() {
        System.out.println("Hello, sir. Ready to gamble?");

        boolean atTable = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your name?");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);
        Shoe shoe = new Shoe(6);
        

        while (atTable) {
            
            int wager = 0;
            while (wager <= 0) {
                try {
                    System.out.println("What is your wager?");
                    wager = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("You did not enter a number, please enter a number.");
                    scanner.nextLine();
                }
            }
            
            System.out.println("Your bet is " + wager);
            
            boolean playing = true;
            // int handNumber = 0;

            // while (playing) {
            //     player.hit(handNumber, shoe.draw());
            // }





            atTable = false;
        }

        scanner.close();
    }




}
