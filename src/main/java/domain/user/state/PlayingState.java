package domain.user.state;

import domain.blackjack.BlackjackCards;
import domain.card.CardDeck;

public class PlayingState implements State {
    @Override
    public void addCard(State state, CardDeck cardDeck, BlackjackCards blackjackCards) {
        blackjackCards.add(cardDeck.handOut());
        changeState(state, blackjackCards);
    }

    @Override
    public void stay(State state) {
        state = new StayState();
    }

    @Override
    public void changeState(State state, BlackjackCards blackjackCards) {
        if(blackjackCards.isBlackjack()) {
            state = new BlackJackState();
        }
        if(blackjackCards.isBust()) {
            state = new BustState();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
