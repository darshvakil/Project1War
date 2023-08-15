package wargame;

import java.util.LinkedList;
import java.util.Collections;

public class GroupOfCards {
    private LinkedList<Card> cards;

    public GroupOfCards() {
        cards = new LinkedList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.pop();
        }
        return null;
    }

    public int getSize() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
