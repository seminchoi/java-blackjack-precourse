package domain.user.state;

import domain.blackjack.Hands;
import domain.card.CardDeck;
import domain.user.Participant;

public class PlayingState implements State {
    @Override
    public void addCard(final Participant participant, final CardDeck cardDeck, final Hands hands) {
        hands.add(cardDeck.handOut());
        changeState(participant, hands);
    }

    @Override
    public void stay(final Participant participant) {
        participant.setState(new StayState());
    }

    @Override
    public void changeState(final Participant participant, final Hands hands) {
        if(hands.isBlackjack()) {
            participant.setState(new BlackJackState());
        }
        if(hands.isBust()) {
            participant.setState(new BustState());
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
