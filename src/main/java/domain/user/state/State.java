package domain.user.state;

import domain.blackjack.BlackjackCards;
import domain.card.CardDeck;

public interface State {
    double BLACKJACK_PROFIT = 1.5;
    double WIN_PROFIT = 1;
    double DRAW_PROFIT = 0;
    double LOSE_PROFIT = -1;

    default double getProfitRate(final BlackjackCards playerHands, final BlackjackCards dealerHands) {
        throw new UnsupportedOperationException("현재 상태에서는 이익율을 계산할 수 없습니다.");
    }

    default void addCard(final State state, final CardDeck cardDeck, final BlackjackCards blackjackCards) {
        throw new UnsupportedOperationException("현재 상태에서는 카드를 추가할 수 없습니다.");
    }

    default void stay(State state) {
        throw new UnsupportedOperationException("현재 상태에서는 Stay 할 수 없습니다.");
    }
    default void changeState(State state, final BlackjackCards blackjackCards) {
    }

    boolean isFinished();
}
