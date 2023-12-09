package domain.blackjack;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hands {
    private final static int MAX_SCORE = 21;
    private final static int ACE_BONUS_SCORE = 10;

    private final List<Card> cards = new ArrayList<>();

    public void add(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int totalScore = 0;
        boolean containAce = false;

        //TODO : 메소드 분리
        for (final Card card : cards) {
            totalScore += card.getScore();
            if (card.isAce()) {
                containAce = true;
            }
        }

        return calculateMaxScore(totalScore, containAce);
    }

    private int calculateMaxScore(final int totalScore, final boolean containAce) {
        if (isRequiredAceBonusScore(totalScore, containAce)) {
            return totalScore + ACE_BONUS_SCORE;
        }
        return totalScore;
    }

    private boolean isRequiredAceBonusScore(final int totalScore, final boolean containAce) {
        return (containAce) && !isBust(totalScore + ACE_BONUS_SCORE);
    }

    public boolean isBust() {
        return isBust(calculateScore());
    }

    private boolean isBust(final int score) {
        return score > MAX_SCORE;
    }

    public boolean isBlackjack() {
        return calculateScore() == MAX_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }
}
