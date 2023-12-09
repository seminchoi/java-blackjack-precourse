package dto;

import domain.user.Dealer;
import domain.user.Player;

import java.util.stream.Collectors;

public class ResultDto {
    private final UserDto userDto;
    private final String score;


    public ResultDto(final Dealer dealer) {
        userDto = new UserDto(
                "딜러",
                dealer.getHands().getCards().stream().map(CardDto::new).collect(Collectors.toList())
        );
        score = convertScore(dealer);
    }

    public ResultDto(final Player player) {
        userDto = new UserDto(player);
        score = convertScore(player);
    }

    private String convertScore(final Dealer dealer) {
        if (dealer.isBust()) {
            return "Bust";
        }
        return String.valueOf(dealer.calculateScore());
    }

    private String convertScore(final Player player) {
        if (player.isBust()) {
            return "Bust";
        }
        return String.valueOf(player.calculateScore());
    }

    @Override
    public String toString() {
        return userDto.toString() + " - 결과: " + score;
    }
}
