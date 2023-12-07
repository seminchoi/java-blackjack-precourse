package domain.blackjack;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.Random;

public class BlackjackMachine {
    private List<Player> players;
    private int turnOfPlayer;
    private List<Card> cards;
    private boolean[] usedCards;
    private Dealer dealer;

    public void init(final List<Player> players) {
        cards = CardFactory.create();
        usedCards = new boolean[cards.size()];
        dealer = new Dealer();
        this.players = players;
    }

    public boolean isPlayerTurn() {
        return turnOfPlayer < players.size();
    }

    public Player getCurrentPlayer() {
        return players.get(turnOfPlayer);
    }

    public void giveCardToPlayer(final boolean wantToTakeCard) {
        if(!wantToTakeCard) {
            turnOfPlayer++;
            return;
        }

        giveCardToPlayer();
    }

    private void giveCardToPlayer() {
        Player player = players.get(turnOfPlayer);
        player.addCard(drawCard());
        if(player.isBust()) {
            turnOfPlayer++;
        }
    }

    public boolean dealerCanReceiveMoreCard() {
        return dealer.canReceiveCard();
    }

    public void giveCardToDealer() {
        dealer.addCard(drawCard());
    }

    private Card drawCard() {
        Card card = null;
        while (card == null) {
            Random random = new Random();
            int index = random.nextInt(cards.size());
            if(!usedCards[index]) {
                card = cards.get(index);
                usedCards[index] = true;
            }
        }

        return card;
    }
}
