package domain.user.state;

import domain.blackjack.Hands;
import domain.card.CardDeck;
import domain.user.Participant;

public class BeforeStartState implements State {
    private static final int INIT_CARD_COUNT = 2;

    @Override
    public void addCard(final Participant participant, final CardDeck cardDeck, final Hands hands) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            hands.add(cardDeck.handOut());
        }
        changeState(participant, hands);
    }

    @Override
    public void changeState(final Participant participant, final Hands hands) {
        participant.setState(new PlayingState());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
