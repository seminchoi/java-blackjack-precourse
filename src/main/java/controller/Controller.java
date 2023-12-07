package controller;

import domain.user.Player;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Controller {
    private static final int MIN_NUMBER_OF_PLAYER = 2;
    private static final int MAX_NUMBER_OF_PLAYER = 8;

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    private void initPlayers() {
        List<String> names = initPlayerNames();
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            addPlayer(players, name);
        }
    }

    private List<String> initPlayerNames() {
        try {
            final String namesInput = inputView.readPlayerNames();
            return parsePlayerNames(namesInput);
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            return initPlayerNames();
        }
    }

    private void addPlayer(final List<Player> players, final String name) {
        try {
            double bettingMoney = inputView.readPlayerBettingMoney(name);
            Player player = new Player(name, bettingMoney);
            players.add(player);
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            addPlayer(players, name);
        }
    }

    private List<String> parsePlayerNames(final String namesInput) {
        final String[] splitNames = namesInput.split(",");
        final List<String> names = Arrays.stream(splitNames)
                .map(String::trim)
                .collect(Collectors.toList());

        validatePlayer(names);
        return names;
    }

    private void validatePlayer(final List<String> playerNames) {
        validateNumberOfPlayer(playerNames);
        validatePlayersName(playerNames);
    }

    private void validateNumberOfPlayer(final List<String> playerNames) {
        if (isInvalidNumberOfPlayer(playerNames)) {
            throw new IllegalArgumentException(
                    String.format(
                            "플레이어 수는 최소 %d 명, 최대 %d 명 입니다.",
                            MIN_NUMBER_OF_PLAYER,
                            MAX_NUMBER_OF_PLAYER
                    )
            );
        }
    }

    private boolean isInvalidNumberOfPlayer(final List<String> playerNames) {
        return playerNames.size() < MIN_NUMBER_OF_PLAYER || playerNames.size() > MAX_NUMBER_OF_PLAYER;
    }

    private void validatePlayersName(final List<String> playerNames) {
        if (containDuplicateName(playerNames)) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private boolean containDuplicateName(final List<String> playerNames) {
        Set<String> names = new HashSet<>();

        for (String playerName : playerNames) {
            if (!names.add(playerName)) {
                return true;
            }
        }

        return false;
    }
}
