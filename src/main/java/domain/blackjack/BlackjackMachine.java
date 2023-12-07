package domain.blackjack;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlackjackMachine {
    private static final int MIN_NUMBER_OF_PLAYER = 2;
    private static final int MAX_NUMBER_OF_PLAYER = 8;

    private final List<Card> cards;
    private final Dealer dealer;
    private final List<Player> players;

    public BlackjackMachine(final List<Player> players) {
        validate(players);

        cards = CardFactory.create();
        dealer = new Dealer();
        this.players = players;
    }

    private void validate(final List<Player> players) {
        validateNumberOfPlayer(players);
        validatePlayersName(players);
    }

    private void validateNumberOfPlayer(final List<Player> players) {
        if(isInvalidNumberOfPlayer(players)) {
            throw new IllegalArgumentException(
                    String.format(
                            "플레이어 수는 최소 %d 명, 최대 %d 명 입니다.",
                            MIN_NUMBER_OF_PLAYER,
                            MAX_NUMBER_OF_PLAYER
                    )
            );
        }
    }

    private boolean isInvalidNumberOfPlayer(final List<Player> players) {
        return players.size() < MIN_NUMBER_OF_PLAYER || players.size() > MAX_NUMBER_OF_PLAYER;
    }

    private void validatePlayersName(final List<Player> players) {
        if(containDuplicateName(players)) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private boolean containDuplicateName(final List<Player> players) {
        Set<String> names = new HashSet<>();

        for (Player player : players) {
            if(!names.add(player.getName())) {
                return true;
            }
        }

        return false;
    }
}
