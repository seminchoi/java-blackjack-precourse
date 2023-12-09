package domain.user.state;

import domain.blackjack.BlackjackCards;
import domain.card.Card;
import domain.card.CardDeck;

public interface State {
    default double getProfitRate() {
        throw new UnsupportedOperationException("현재 상태에서는 이익율을 계산할 수 없습니다.");
    }

    default void addCard(final CardDeck cardDeck, final BlackjackCards blackjackCards) {
        throw new UnsupportedOperationException("현재 상태에서는 카드를 추가할 수 없습니다.");
    }

    void changeState(State state, final BlackjackCards blackjackCards);
    boolean isFinished();
}
