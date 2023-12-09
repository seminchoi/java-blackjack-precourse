package domain.user;

import domain.blackjack.Hands;
import domain.card.CardDeck;
import domain.user.state.BeforeStartState;
import domain.user.state.State;

public abstract class Participant {
    protected final Hands hands = new Hands();
    protected final String name;
    protected State state;

    public Participant(String name) {
        this.name = name;
        state = new BeforeStartState();
    }

    public void addCard(final CardDeck cardDeck) {
        state.addCard(this, cardDeck, hands);
    }

    public boolean isBust() {
        return hands.isBust();
    }

    public boolean isPlaying() {
        return !state.isFinished();
    }

    public int calculateScore() {
        return hands.calculateScore();
    }

    public void setState(State state) {
        this.state = state;
    }

    public Hands getHands() {
        return hands;
    }

    public String getName() {
        return name;
    }
}
