package domain.user.state;

import domain.blackjack.BlackjackCards;
import domain.card.CardDeck;

public class BeforeStartState implements State {
    private static final int INIT_CARD_COUNT = 2;

    @Override
    public void addCard(final CardDeck cardDeck, final BlackjackCards blackjackCards) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            blackjackCards.add(cardDeck.handOut());
        }
        changeState(blackjackCards);
    }

    @Override
    public void changeState(final BlackjackCards blackjackCards) {
        State curState = this;
        curState = new PlayingState();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
