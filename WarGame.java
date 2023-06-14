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
