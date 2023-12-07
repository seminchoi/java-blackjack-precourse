package domain.blackjack;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import dto.BenefitDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlackjackMachine {
    private static final int INIT_CARDS_COUNT = 2;
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
        initCards();
    }

    private void initCards() {
        for (int i = 0; i < INIT_CARDS_COUNT; i++) {
            dealer.addCard(drawCard());
        }
        for (Player player : players) {
            for (int i = 0; i < 2; i++) {
                player.addCard(drawCard());
            }
        }
    }

    public boolean isPlayerTurn() {
        return turnOfPlayer < players.size();
    }

    public Player getCurrentPlayer() {
        return players.get(turnOfPlayer);
    }

    public void giveCardToPlayer(final boolean wantToTakeCard) {
        if (!wantToTakeCard) {
            turnOfPlayer++;
            return;
        }

        giveCardToPlayer();
    }

    private void giveCardToPlayer() {
        Player player = players.get(turnOfPlayer);
        player.addCard(drawCard());
        if (player.isBust()) {
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
            if (!usedCards[index]) {
                card = cards.get(index);
                usedCards[index] = true;
            }
        }

        return card;
    }

    public List<BenefitDto> calculateBenefits() {
        final List<BenefitDto> benefitDtos = new ArrayList<>();
        for (final Player player : players) {
            benefitDtos.add(calculateBenefit(player));
        }
        benefitDtos.add(0, new BenefitDto("딜러", calculateDealerBenefit(benefitDtos)));
        return benefitDtos;
    }

    private BenefitDto calculateBenefit(final Player player) {
        if (isPlayerBlackJackOnly(player)) {
            return new BenefitDto(player.getName(), player.getBettingMoney() * 1.5D);
        }
        if (isPlayerWin(player)) {
            return new BenefitDto(player.getName(), player.getBettingMoney());
        }
        if(isPlayerDraw(player)) {
            return new BenefitDto(player.getName(), 0D);
        }
        if(isPlayerLose(player)) {
            return new BenefitDto(player.getName(), player.getBettingMoney() * (-1D));
        }
        throw new InternalError("수익 계산 중 오류가 발생하였습니다.");
    }

    private boolean isPlayerBlackJackOnly(final Player player) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private boolean isPlayerWin(final Player player) {
        return !player.isBust() && (dealer.isBust() || dealer.calculateScore() < player.calculateScore());
    }

    private boolean isPlayerDraw(final Player player) {
        return !player.isBust() && (player.calculateScore() == dealer.calculateScore());
    }

    private boolean isPlayerLose(final Player player) {
        return player.isBust() || (!dealer.isBust() && dealer.calculateScore() > player.calculateScore());
    }

    private double calculateDealerBenefit(final List<BenefitDto> benefitDtos) {
        double dealerBenefit = 0;
        for (BenefitDto benefitDto : benefitDtos) {
            dealerBenefit += benefitDto.getAmount();
        }
        return dealerBenefit;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
