package dto;

import domain.user.Dealer;
import domain.user.Player;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class UserDto {
    private final String name;
    private final List<CardDto> cards;

    public UserDto(Dealer dealer) {
        this.name = "딜러";
        this.cards = Collections.singletonList(new CardDto(dealer.getFirstCard()));
    }

    public UserDto(Player player) {
        this.name = player.getName();
        this.cards = player.getCards().stream().map(CardDto::new).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ");
        for (CardDto card : cards) {
            joiner.add(card.toString());
        }
        return name + ": " + joiner.toString();
    }
}
