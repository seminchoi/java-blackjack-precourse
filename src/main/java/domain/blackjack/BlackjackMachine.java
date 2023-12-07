package domain.blackjack;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;

public class BlackjackMachine {
    private List<Player> players;
    private List<Card> cards;
    private boolean[] usedCards;
    private Dealer dealer;

    public void init(final List<Player> players) {
        cards = CardFactory.create();
        usedCards = new boolean[cards.size()];
        dealer = new Dealer();
        this.players = players;
    }
}
