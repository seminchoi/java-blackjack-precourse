package domain.user;

import domain.blackjack.BlackjackCards;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private static final int CARD_RECEIVE_THRESHOLD = 16;

    private final BlackjackCards cards = new BlackjackCards();

    public Dealer() {}

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean canReceiveCard() {
        return calculateScore() <= CARD_RECEIVE_THRESHOLD;
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public Card getFirstCard() {
        return cards.getFirstCard();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }
}
