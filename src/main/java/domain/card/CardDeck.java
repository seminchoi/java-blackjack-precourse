package domain.card;

import java.util.Queue;

public class CardDeck {
    private final Queue<Card> cards;

    public CardDeck() {
        cards = CardFactory.create();
    }

    public Card handOut() {
        return cards.poll();
    }
}
