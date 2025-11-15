package com.assc.blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Model {
    
    public static void main(String[] args) {
        run();
    }
    
    public static void run() {
        System.out.println("Hello, sir. Ready to gamble?");

        int numDecks = 6;
        boolean atTable = true;
        boolean blackjackEligible = true;
        boolean changedDealerAce = false;
        boolean hasCompletelyBusted = true;

        Scanner scanner = new Scanner(System.in);

        Shoe shoe = new Shoe(numDecks);
        shoe.cutDeck(30);   //can change this to get user input and make it more immersive
        Hand dealerHand = new Hand();
        ArrayList<Hand> completedHands = new ArrayList<Hand>();
        
        System.out.println("What is your name?");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);

        while (atTable) {
            
            int wager = 0;
            while (wager <= 0) {
                try {
                    System.out.println("What is your wager?");
                    wager = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("You did not enter a number, please enter a number.");
                    scanner.nextLine();
                }

                if (wager > player.getMoney()) {
                    System.out.println("you don't have enough money to wager $" + wager);
                    wager = 0;
                }
            }
            
            System.out.println("Your bet is $" + wager + "\n");
            
            boolean playing = true;

            //deal in
            player.addHand(wager);
            player.hit(0, shoe.draw());
            dealerHand.addCard(shoe.draw());

            while (playing) {
                player.hit(-1, shoe.draw());

                //initially printing dealer hand (doing this in the while loop to deal the cards correctly :D )
                if (dealerHand.handSize() == 1) {
                    dealerHand.addCard(shoe.draw());
                    System.out.println("Dealer hand:");
                    dealerHand.printDealer();
                    System.out.println();
                }

                System.out.println("playerHand" + (player.numberOfHands()-1) + ":");

                player.getHand(-1).printHand();

                //checking for bust
                if (player.getHand(-1).checkForBust()) {
                    player.getHand(-1).setWin(0);
                    completedHands.add(player.popHand());
                    break;
                }

                //blackjack condition
                if (player.getHand(-1).getHandValue() == 21) {
                    if (blackjackEligible) {    //if the player got blackjack
                        player.getHand(0).setWin(2.5);
                        completedHands.add(player.popHand());
                        break;
                    }
                    else {  //if the player reached 21 on more than 2 cards
                        completedHands.add(player.popHand());
                        break;
                    }
                }
                blackjackEligible = false;

                System.out.print("\nhit [h], stay [s]");
                if (player.ableToSplit()) {
                    System.out.print(", split [v]");
                }
                if (player.getHand(-1).handSize() == 1 || player.numberOfHands() == 1) {
                    System.out.print(", double down [d]");
                }
                System.out.println("\nWhat will you do?");
                String choice = scanner.nextLine(); 

                switch (choice) {
                    case "h":
                        System.out.println("--[you hit]--");
                        break;
                    case "s":
                        System.out.println("--[you stay]--");
                        completedHands.add(player.popHand());
                        playing = false;
                        break;
                    case "v":
                        player.splitHand();
                        System.out.println("--[you split]--");
                        break;
                    case "d":
                        wager *= 2;
                        System.out.println("--[you double down]--");
                        player.hit(-1, shoe.draw());
                        break;
                }

                if (player.numberOfHands() == 0) {
                    playing = false;
                }
            }

            System.out.println("End of your turn\n\n");

            System.out.println("Dealer hand:");
            dealerHand.printHand();

            for (Hand hand : completedHands) {
                if (hand.getHandValue() <= 21) {
                    hasCompletelyBusted = false;
                    break;
                }
            }

            //don't even bother with the dealer if the player has already lost
            if (!hasCompletelyBusted) {
                //deal dealer if they don't have 17
                while (!dealerHand.has17() || ((dealerHand.getHandValue() == 17) && !changedDealerAce)) {   //FUCK this shouldn't have been this hard
                    System.out.println("--[dealer hits]--");
                    dealerHand.addCard(shoe.draw());
                    
                    if (dealerHand.containsAce() && !changedDealerAce) {
                        dealerHand.checkForBust();
                        changedDealerAce = true;
                    }

                    System.out.println("Dealer hand after hit:");
                    dealerHand.printHand();
                }
                System.out.println("Dealer ended with: " +  dealerHand.getHandValue());
                System.out.println();
            }
            hasCompletelyBusted = true;

            //win conditions
            for (Hand hand : completedHands) {
                if (hand.getWinMultiplier() == 2.5) {
                    //nothing happens because we already set winMultiplier when dealer got blackjack
                    System.out.println("[YOU GOT BLACKJACK]");
                }
                //if you bust
                else if (hand.getHandValue() > 21) {
                    hand.setWin(0);
                    System.out.println("[YOU BUST]");
                } 
                //if the dealer busts
                else if (dealerHand.getHandValue() > 21) {
                    hand.setWin(2);
                    System.out.println("[YOU WIN (dealer busts)]");
                }
                //if you win against the dealer
                else if (hand.getHandValue() > dealerHand.getHandValue()) {
                    hand.setWin(2);
                    System.out.println("[YOU WIN (beat dealer)]");
                }
                //if you push with the dealer
                else if (hand.getHandValue() == dealerHand.getHandValue()) {
                    hand.setWin(1);
                    System.out.println("[YOU PUSH]");
                }
                //else the dealer beats you
                else {
                    hand.setWin(0);
                    System.out.println("[YOU LOSE]");
                }
            }

            //TODO: pay player

            //clear the table
            completedHands.clear();
            dealerHand.clearHand();

            //reached your deck cut
            if (shoe.cardsRemaining() < shoe.getDeckCut()) {
                System.out.println("[CHANGING SHOE]");
                shoe = new Shoe(numDecks);
            }

            //keep playing?
            System.out.println("\nkeep playing??? [y/n]");
            String keepPlaying = scanner.nextLine();
            if (keepPlaying.equals("n")) {
                atTable = false;
            }
        }

        scanner.close();
    }

    public static void printHands(Player player) {
        for (Hand hand : player.getHandList()) {
            hand.printHand();
        }
    }
}
