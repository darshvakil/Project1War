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
private void createDeck() {
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
        ArrayList<Player> players = getPlayers();
        int numPlayers = players.size();
        int numCards = deck.getSize();

        for (int i = 0; i < numCards; i++) {
            Player currentPlayer = players.get(i % numPlayers);
            Card card = deck.drawCard();
            currentPlayer.addCardToHand(card);
        }
    }

    private void playRound() {
        ArrayList<Player> players = getPlayers();
        ArrayList<Card> cardsInPlay = new ArrayList<>();

        for (Player player : players) {
            if (player.hasCards()) {
                Card playedCard = player.playCard();
                cardsInPlay.add(playedCard);
                System.out.println(player.getName() + " plays: " + playedCard.toString());
            }
        }

        Card highestCard = getHighestCard(cardsInPlay);
        System.out.println("The highest card is: " + highestCard.toString());

        for (Player player : players) {
            if (player.hasCards()) {
                if (player.playCard() != highestCard) {
                    System.out.println(player.getName() + " loses a card");
                }
            }
        }
    }

    private Card getHighestCard(ArrayList<Card> cards) {
        Card highestCard = cards.get(0);

        for (Card card : cards) {
            if (getCardValue(card) > getCardValue(highestCard)) {
                highestCard = card;
            }
        }

        return highestCard;
    }
