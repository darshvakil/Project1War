package wargame;

import java.util.LinkedList;
import java.util.Scanner;
import wargame.Card.Rank;
import wargame.Card.Suit;

public class Game {
    private Player player1;
    private Player player2;
    private GroupOfCards deck;
    private Scanner scanner;
    private GroupOfCards player1Deck;
    private GroupOfCards player2Deck;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.deck = createDeck();
        this.scanner = new Scanner(System.in);
        this.player1Deck = new GroupOfCards();
        this.player2Deck = new GroupOfCards();
        dealCards(player1Deck, player2Deck);
    }

    private GroupOfCards createDeck() {
        GroupOfCards newDeck = new GroupOfCards();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                newDeck.addCard(new Card(rank, suit));
            }
        }
        newDeck.shuffle();
        return newDeck;
    }

    private void dealCards(GroupOfCards player1Deck, GroupOfCards player2Deck) {
        int deckSize = deck.getSize();
        for (int i = 0; i < deckSize / 2; i++) {
            player1Deck.addCard(deck.drawCard());
            player2Deck.addCard(deck.drawCard());
        }
    }

    private Card playRound() {
        Card player1Card = player1Deck.drawCard();
        Card player2Card = player2Deck.drawCard();

        System.out.println("Player 1 draws: " + player1Card);
        System.out.println("Player 2 draws: " + player2Card);

        if (player1Card.compareTo(player2Card) > 0) {
            player1Deck.addCard(player1Card);
            player1Deck.addCard(player2Card);
            player1.increaseScore();
            System.out.println("Player 1 wins this round!");
        } else if (player1Card.compareTo(player2Card) < 0) {
            player2Deck.addCard(player1Card);
            player2Deck.addCard(player2Card);
            player2.increaseScore();
            System.out.println("Player 2 wins this round!");
        } else {
            System.out.println("It's a tie!");
        }

        return getHighestCard(player1Card, player2Card);
    }

    private Card getHighestCard(Card card1, Card card2) {
        return (card1.compareTo(card2) > 0) ? card1 : card2;
    }

    public void play() {
        while (!player1Deck.isEmpty() && !player2Deck.isEmpty()) {
            System.out.println("\nPlayer 1 has " + player1Deck.getSize() + " cards. Player 2 has " + player2Deck.getSize() + " cards.");
            System.out.println("Press Enter to draw a card...");
            scanner.nextLine();

            Card highestCard = playRound();
            System.out.println("Highest card: " + highestCard);

            System.out.println("Do you want to continue? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();
            if ("no".equals(response)) {
                System.out.println("Game ended successfully.");
                return;  // Exit the play loop
            }
        }

        if (player1Deck.isEmpty()) {
            System.out.println("Player 2 wins the game!");
        } else if (player2Deck.isEmpty()) {
            System.out.println("Player 1 wins the game!");
        } else {
            System.out.println("Game stopped by the players.");
        }
        scanner.close();
    }
}
