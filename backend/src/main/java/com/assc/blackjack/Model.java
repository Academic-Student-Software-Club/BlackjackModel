package com.assc.blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

//TODO: insurance, surrender

public class Model {
    
    public static void main(String[] args) {
        Hand playerTestHand = new Hand();  

        String[] playerCards = {"10", "10"};   //playerCards should have strings (lowercase) ex: {"1", "8", "ace", "jack"}
        playerTestHand.artificialHand(playerCards);


        // playerTestHand = null;  //  off/on test switch
        run(playerTestHand);
    }
    
    public static void run(Hand playerTestHand) {
        System.out.println("Ready to gamble?");

        int numDecks = 6;
        boolean atTable = true;
        boolean blackjackEligible = true;
        boolean hasCompletelyBusted = true; //used for checking if we need to even deal the dealer
        boolean dealerBlackjack = false;
        boolean playerBlackjack = false;
        boolean playing = true;

        Scanner scanner = new Scanner(System.in);

        //initializing objects
        Shoe shoe = new Shoe(numDecks);
        shoe.cutDeck(30);   //can change this to get user input and make it more immersive
        Hand dealerHand = new Hand();
        ArrayList<Hand> completedHands = new ArrayList<Hand>();
        
        System.out.println("What is your name?");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);

        while (atTable) {
            //resetting variables
            blackjackEligible = true;
            hasCompletelyBusted = true;
            dealerBlackjack = false;
            playerBlackjack = false;
            playing = true;
            
            //terminal user input check
            double wager = 0;
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
            
            //updates player's bank
            player.subtractMoney(wager);
            System.out.println("Your bet is $" + wager + "\n");

            //deal in player
            if (playerTestHand == null) {
                player.addHand();
                player.hit(shoe.draw());
                dealerHand.addCard(shoe.draw());
                player.hit(shoe.draw());
            } else {
                System.out.println("[USING ARTIFICIAL TESTING HAND]");  //use testing hand from main method
                // playerTestHand.reduceToTwo();
                player.addHand(playerTestHand);
                playerTestHand.calculateHandValue();
                dealerHand.addCard(shoe.draw());
            }

            dealerHand.addCard(shoe.draw());
            System.out.println("Dealer hand:");
            dealerHand.printDealer();
            System.out.println();

            if (dealerHand.getHandValue() == 21) {
                dealerBlackjack = true;
            }

            //main loop for player turn
            while (playing) {

                System.out.println("Hand " + (player.numberOfHands()) + ":");   //printing player hand
                player.getHand(-1).printHand();

                //checking for bust
                if (player.getHand(-1).checkCalculateBust()) {
                    player.getHand(-1).setWin(0);
                    completedHands.add(player.popHand());
                    break;
                }

                //blackjack condition
                //break out of each condition b/c player can only get blackjack with their one and only hand
                if (player.getHand(0).getHandValue() == 21) {
                    if (blackjackEligible && !dealerBlackjack) {    //if the player got blackjack
                        player.getHand(0).setWin(2.5);
                        completedHands.add(player.popHand());
                        playerBlackjack = true;
                        break;
                    } else if (blackjackEligible && dealerBlackjack) {  //push if dealer also has blackjack
                        player.getHand(0).setWin(1);
                        completedHands.add(player.popHand());
                        playerBlackjack = true;
                        break;
                    }
                    else {  //if the player reached 21 on more than 2 cards, don't blackjack
                        completedHands.add(player.popHand());
                        break;
                    }
                }
                blackjackEligible = false;  //resetting variable b/c player only gets blackjack w/ first hand

                //checking if able to split or double down
                System.out.print("\nhit [h], stay [s]");
                if (player.ableToSplit()) {
                    System.out.print(", split [v]");
                }
                if (player.getHand(-1).handSize() == 1 || player.numberOfHands() == 1) {
                    System.out.print(", double down [d]");
                }
                System.out.println("\nWhat will you do?");
                String choice = scanner.nextLine(); 

                boolean playerHasChosen = false;
                while (!playerHasChosen) {  //player decision to hit, stay, split, dd
                    playerHasChosen = true;
                    if (choice.equals("h") || choice.equals("H")) { //HIT
                        System.out.println("--[you hit]--");
                        player.hit(shoe.draw());
                    } else if (choice.equals("s") || choice.equals("S")) {  //STAY
                        System.out.println("--[you stay]--");
                        completedHands.add(player.popHand());
                    } else if (choice.equals("v") || choice.equals("v")) {  //SPLIT
                        System.out.println("--[you split]--");
                        player.splitHand();
                        player.subtractMoney(wager);
                    } else if (choice.equals("d") || choice.equals("d")) {  //DOUBLE-DOWN
                        System.out.println("--[you double down]--");
                        player.hit(shoe.draw());
                        player.getHand(-1).setWager(wager*2);
                        player.subtractMoney(wager);
                        player.getHand(-1).printHand();
                        completedHands.add(player.popHand());
                    } else {
                        System.out.println("you chose none of the options, type your decision again");
                        playerHasChosen = false;
                    }
                }

                //if all player's hands have been dealt...
                if (player.numberOfHands() == 0) {
                    System.out.println("player.numberOfHands() -> " + player.numberOfHands());
                    playing = false;
                }
            }

            System.out.println("End of your turn\n\n");

            System.out.println("Dealer hand:");
            dealerHand.printHand();
            System.out.println();

            //seeing if all player hands have busted
            for (Hand hand : completedHands) {
                if (hand.getHandValue() <= 21) {
                    hasCompletelyBusted = false;
                    break;
                }
            }

            //DEAL DEALER
            //don't even bother with the dealer if the player has already lost or they player got blackjack
            if (!(hasCompletelyBusted || playerBlackjack)) {
                
                //deal dealer if they don't have 17 
                while (dealerHand.getHandValue() <= 17 || !(dealerHand.getHandValue() > player.getBestHand().getHandValue())) {
                    //hard 17 is the only stay conditions within the loop
                    if (dealerHand.getHandValue() == 17 && !dealerHand.containsAce()) {
                        break;
                    }

                    System.out.println("--[dealer hits]--");
                    dealerHand.addCard(shoe.draw());
                    dealerHand.checkCalculateBust();    //soften ace if the dealer busts with an 11 ace

                    System.out.println("Dealer hand after hit:");
                    dealerHand.printHand();
                }
                System.out.println("Dealer ended with: " +  dealerHand.getHandValue());
                System.out.println();
            }
            hasCompletelyBusted = true; //resetting variable

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

            //PAYOUT
            //win conditions should be set by now
            int payout = 0;
            int netGain = 0;
            for (Hand hand : completedHands) {
                System.out.println("{{hand win multiplier: " + hand.getWinMultiplier() + "}}");
                payout += (wager * hand.getWinMultiplier());
                netGain += (wager * (hand.getWinMultiplier()-1));
            }

            System.out.println(payout);
            System.out.println(netGain);

            player.payPlayer(payout);

            if (netGain > 0) {
                System.out.println("YOU EARNED $" + netGain + "!");
            } else if (netGain < 0) {
                System.out.println("RESULT: -$" + (netGain*(-1)) );
            }

            System.out.println("Current Balance = " + player.getMoney());

            //clear the table
            completedHands.clear();
            dealerHand.clearHand();

            //reached your deck cut
            if (shoe.cardsRemaining() < shoe.getDeckCut()) {
                System.out.println("[CHANGING SHOE]");
                shoe = new Shoe(numDecks);
            }

            if (player.getMoney() <= 0) {
                System.out.println("YOU ARE BANKRUPT, GET THE FUCK OUT OF THE CASINO!!!");
                break;
            }

            //keep playing?
            System.out.println("\nkeep playing??? [y/n]");
            String keepPlaying = scanner.nextLine();
            if (keepPlaying.equals("n") || keepPlaying.equals("N") || keepPlaying.equals("no") || keepPlaying.equals("No") || keepPlaying.equals("NO")) {
                atTable = false;
            }
        }

        scanner.close();
    }
}
