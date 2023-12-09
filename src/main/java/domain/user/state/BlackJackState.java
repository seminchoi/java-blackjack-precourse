package domain.user.state;

import domain.blackjack.Hands;

public class BlackJackState implements State {
    @Override
    public double getProfitRate(Hands playerHands, Hands dealerHands) {
        if(dealerHands.isBlackjack()) {
            return DRAW_PROFIT;
        }
        return BLACKJACK_PROFIT;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
