package domain.user.state;

import domain.blackjack.Hands;

public class BustState implements State {
    @Override
    public double getProfitRate(Hands playerHands, Hands dealerHands) {
        return LOSE_PROFIT;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
