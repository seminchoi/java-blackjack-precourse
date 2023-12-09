package domain.user.state;

import domain.blackjack.BlackjackCards;
import domain.card.CardDeck;

public class PlayingState implements State {
    @Override
    public void addCard(CardDeck cardDeck, BlackjackCards blackjackCards) {
        blackjackCards.add(cardDeck.handOut());
        changeState(blackjackCards);
    }

    @Override
    public void stay() {
        State curState = this;
        curState = new StayState();
    }

    @Override
    public void changeState(BlackjackCards blackjackCards) {
        State curState = this;
        if(blackjackCards.isBlackjack()) {
            curState = new BlackJackState();
        }
        if(blackjackCards.isBust()) {
            curState = new BustState();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
