package domain.user.state;

import domain.blackjack.Hands;
import domain.card.CardDeck;
import domain.user.Participant;

public interface State {
    double BLACKJACK_PROFIT = 1.5;
    double WIN_PROFIT = 1;
    double DRAW_PROFIT = 0;
    double LOSE_PROFIT = -1;

    default double getProfitRate(final Hands playerHands, final Hands dealerHands) {
        throw new UnsupportedOperationException("현재 상태에서는 이익율을 계산할 수 없습니다.");
    }

    default void addCard(final Participant participant, final CardDeck cardDeck, final Hands hands) {
        throw new UnsupportedOperationException("현재 상태에서는 카드를 추가할 수 없습니다.");
    }

    default void stay(final Participant participant) {
        throw new UnsupportedOperationException("현재 상태에서는 Stay 할 수 없습니다.");
    }
    default void changeState(final Participant participant, final Hands hands) {
    }

    boolean isFinished();
}
