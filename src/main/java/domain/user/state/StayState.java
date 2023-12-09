package domain.user.state;

import domain.blackjack.Hands;

public class StayState implements State {
    @Override
    public double getProfitRate(final Hands playerHands, final Hands dealerHands) {
        if (!dealerHands.isBust() && playerHands.calculateScore() < dealerHands.calculateScore()) {
            return LOSE_PROFIT;
        }
        if (dealerHands.isBust() || playerHands.calculateScore() > dealerHands.calculateScore()) {
            return WIN_PROFIT;
        }
        if(playerHands.calculateScore() == dealerHands.calculateScore()) {
            return DRAW_PROFIT;
        }
        throw new InternalError("이익율 계산에 실패했습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
