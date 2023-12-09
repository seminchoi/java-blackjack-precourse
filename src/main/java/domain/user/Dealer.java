package domain.user;

import domain.card.Card;

import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends Participant{
    public Dealer() {
        super("딜러");
    }

    public double calculateProfit(List<Double> profits) {
        return profits.stream().mapToDouble(profit -> (-1) * profit).sum();
    }

    public Card getFirstCard() {
        return hands.getFirstCard();
    }
}
