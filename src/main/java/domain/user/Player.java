package domain.user;

import domain.blackjack.BlackjackCards;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final static double MIN_BETTING_MONEY = 10;

    private final String name;
    private final double bettingMoney;
    private final BlackjackCards cards = new BlackjackCards();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    private void validate(final double bettingMoney) {
        if(bettingMoney < MIN_BETTING_MONEY) {
            throw new IllegalArgumentException(
                    String.format("배팅 머니는 %f 이상이어야 합니다.", MIN_BETTING_MONEY)
            );
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cards.isBust();
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

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }
}
