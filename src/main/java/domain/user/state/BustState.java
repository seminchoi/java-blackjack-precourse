package domain.user.state;

import domain.blackjack.BlackjackCards;

public class BustState implements State {
    @Override
    public double getProfitRate(BlackjackCards playerHands, BlackjackCards dealerHands) {
        return LOSE_PROFIT;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
