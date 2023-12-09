package domain.user;

import domain.blackjack.Hands;

/**
 * 게임 참여자를 의미하는 객체
 */
// 헌치님 코드 참고
public class Player extends Participant {
    private final static double MIN_BETTING_MONEY = 10;

    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        super(name);
        validate(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validate(final double bettingMoney) {
        if (bettingMoney < MIN_BETTING_MONEY) {
            throw new IllegalArgumentException(
                    String.format("배팅 머니는 %f 이상이어야 합니다.", MIN_BETTING_MONEY)
            );
        }
    }

    public void stay() {
        state.stay(this);
    }

    public double calculateProfit(final Hands dealerHands) {
        return bettingMoney * state.getProfitRate(hands, dealerHands);
    }


}
