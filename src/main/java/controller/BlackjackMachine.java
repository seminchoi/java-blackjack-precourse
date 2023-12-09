package controller;

import domain.card.CardDeck;
import domain.user.Dealer;
import domain.user.Player;
import dto.BenefitDto;
import dto.ResultDto;
import dto.UserDto;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackjackMachine {
    private static final int MIN_NUMBER_OF_PLAYER = 2;
    private static final int MAX_NUMBER_OF_PLAYER = 8;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackMachine(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final CardDeck cardDeck = new CardDeck();
        final Dealer dealer = new Dealer();
        final List<Player> players = initPlayers();
        initHands(cardDeck, dealer, players);
        playGame(cardDeck, dealer, players);
        makeResult(dealer, players);
    }

    private List<Player> initPlayers() {
        List<String> names = initPlayerNames();
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            addPlayer(players, name);
        }
        return players;
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

    private void initHands(final CardDeck cardDeck, final Dealer dealer, final List<Player> players) {
        dealer.addCard(cardDeck);
        for (Player player : players) {
            player.addCard(cardDeck);
        }
        printInitHands(dealer, players);
    }

    private void printInitHands(final Dealer dealer, final List<Player> players) {
        final List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto(dealer));
        for (final Player player : players) {
            userDtos.add(new UserDto(player));
        }
        outputView.printInitStatus(userDtos);
    }

    private void playGame(final CardDeck cardDeck, final Dealer dealer, final List<Player> players) {
        doPlayersTurn(cardDeck, players);
        doDealerTurn(cardDeck, dealer);
    }

    private void doPlayersTurn(final CardDeck cardDeck, final List<Player> players) {
        for (final Player player : players) {
            try {
                giveCardToPlayer(cardDeck, player);
            } catch (IllegalArgumentException e) {
                outputView.printError(e);
            }
        }
    }

    private void giveCardToPlayer(final CardDeck cardDeck, final Player player) {
        while (player.isPlaying()) {
            final boolean intention = inputView.readIntention(player.getName());
            if(intention) {
                player.addCard(cardDeck);
            }
            if(!intention) {
                player.stay();
            }
        }
    }

    private void doDealerTurn(final CardDeck cardDeck, final Dealer dealer) {
        while (dealer.isPlaying()) {
            dealer.addCard(cardDeck);
            outputView.printDealerTurnStatus();
        }
    }

    private void makeResult(final Dealer dealer, final List<Player> players) {
        makeUsersResult(dealer, players);
        makeUsersBenefit(dealer, players);
    }

    private void makeUsersResult(final Dealer dealer, final List<Player> players) {
        final List<ResultDto> resultDtos = new ArrayList<>();
        resultDtos.add(new ResultDto(dealer));

        for (final Player player : players) {
            resultDtos.add(new ResultDto(player));
        }

        outputView.printUsersResult(resultDtos);
    }

    private void makeUsersBenefit(final Dealer dealer, final List<Player> players) {
        final List<BenefitDto> benefitDtos = calculateBenefits(dealer, players);
        outputView.printUsersBenefit(benefitDtos);
    }

    public List<BenefitDto> calculateBenefits( final Dealer dealer, final List<Player> players) {
        final List<BenefitDto> benefitDtos = new ArrayList<>();
        for (final Player player : players) {
            benefitDtos.add(
                    new BenefitDto(player.getName(), player.calculateProfit(dealer.getHands()))
            );
        }
        benefitDtos.add(
                0, new BenefitDto("딜러", dealer.calculateProfit(
                        benefitDtos.stream()
                                .map(BenefitDto::getAmount)
                                .collect(Collectors.toList()))
                )
        );
        return benefitDtos;
    }
}
