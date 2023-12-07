package domain.blackjack;

import domain.card.Card;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.List;

public class BlackjackCards {
    private final static int MAX_SCORE = 21;
    private final static int ACE_BONUS_SCORE = 10;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(final Card card) {
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
        if(isRequiredAceBonusScore(totalScore, containAce)) {
                return totalScore + ACE_BONUS_SCORE;
        }
        return totalScore;
    }

    private boolean isRequiredAceBonusScore(final int totalScore, final boolean containAce) {
        return (containAce) && (totalScore + ACE_BONUS_SCORE <= MAX_SCORE);
    }
}
