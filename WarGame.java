import java.util.ArrayList;
import java.util.Collections;

/**
 * A class to represent a playing card
 */
class Card {
    private final String rank;
    private final String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
/**
 * A class to represent a group of cards
 */
class GroupOfCards {
    private ArrayList<Card> cards;

    public GroupOfCards() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void shuffle() {
    //implementation to shuffle all cards 
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public int getSize() {
        return cards.size();
    }
}

/**
 * A class to represent a player
 */
class Player {
    private final String name;
    private final GroupOfCards hand;

    public Player(String name) {
        this.name = name;
        this.hand = new GroupOfCards();
    }

    public void addCardToHand(Card card) {
       //implementation to add card to hand for the player who wins the round
    }

    public Card playCard() {
    //implentation for each player to play a card 
    }

    public boolean hasCards() {
    //number of cards player will have to decide winner at the end
    }

    public String getName() {
        return name;
    }
}
/**
 * A class to represent the game
 */
abstract class Game {

    private final String name;//the title of the game
    private ArrayList<Player> players;// the players of the game

    public Game(String name) {
        this.name = name;
        players = new ArrayList<>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the players of this game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players of this game
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    
    public abstract void play(); 
    
    public abstract void declareWinner();

}

/**
 * A class to represent the game of War
 */
class WarGame extends Game {
    private final GroupOfCards deck;

    public WarGame() {
        super("War");
        deck = new GroupOfCards();
    }

    @Override
    public void play() {
        // Create and shuffle the deck
        createDeck();
        deck.shuffle();

        // Deal cards to players
        dealCards();

        // Start the game
        while (atLeastTwoPlayersHaveCards()) {
            playRound();
        }

        // Declare the winner
        declareWinner();
    }
private void createDeck() { //creates a basic deck 
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        for (String suit : suits) {
            for (String rank : ranks) {
                Card card = new Card(rank, suit);
                deck.addCard(card);
            }
        }
    }

    private void dealCards() {
    //implementation to deal Cards to players
    }

    private void playRound() {
       //implementation to run a single round / turn andd determine who has higher card
    }

    private Card getHighestCard(ArrayList<Card> cards) { //implentation to determine the highest value card from both cards drawn by players
    }
    
    private int getCardValue(Card card) {
    //implementation to set values for card
    }

    private boolean atLeastTwoPlayersHaveCards(); {} //oversees who is still in the game
    

    @Override
//base implementation on how winner will be decided. may change accordingly 
    public void declareWinner() { 
        Player winner = null;
        for (Player player : getPlayers()) {
            if (player.hasCards()) {
                if (winner == null) {
                    winner = player;
                } else {
                    System.out.println("It's a tie!");
                    return;
                }
            }
        }
        System.out.println("The winner is: " + winner.getName());
    }

    public void addPlayer(String name) { //add player to game 
        Player player = new Player(name);
        getPlayers().add(player);
    }
}

}
