package domain.user.state;

import domain.blackjack.BlackjackCards;

public class BlackJackState implements State {
    @Override
    public double getProfitRate(BlackjackCards playerHands, BlackjackCards dealerHands) {
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
