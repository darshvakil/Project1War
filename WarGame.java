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
        Collections.shuffle(cards);
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
        hand.addCard(card);
    }

    public Card playCard() {
        return hand.drawCard();
    }

    public boolean hasCards() {
        return hand.getSize() > 0;
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

    /**
     * Play the game. This might be one method or many method calls depending on your game.
     */
    public abstract void play();

    /**
     * When the game is over, use this method to declare and display a winning player.
     */
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
