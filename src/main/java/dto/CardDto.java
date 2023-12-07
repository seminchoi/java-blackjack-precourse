package dto;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

public class CardDto {
    private final String symbol;
    private final String type;

    public CardDto(Card card) {
        this.symbol = convertSymbol(card.getSymbol());
        this.type = convertType(card.getType());
    }

    private String convertSymbol(final Symbol symbol) {
        if(symbol.equals(Symbol.ACE)) {
            return "A";
        }
        if(symbol.equals(Symbol.KING)) {
            return "K";
        }
        if(symbol.equals(Symbol.QUEEN)) {
            return "Q";
        }
        if(symbol.equals(Symbol.JACK)) {
            return "J";
        }
        return String.valueOf(symbol.getScore());
    }

    private String convertType(Type type) {
        if(type.equals(Type.CLUB)) {
            return "클로버";
        }
        if(type.equals(Type.DIAMOND)) {
            return "다이아몬드";
        }
        if(type.equals(Type.HEART)) {
            return "하트";
        }
        if(type.equals(Type.SPADE)) {
            return "스페이드";
        }
        throw new InternalError("카드 타입이 잘못되었습니다. (DTO 변환중)");
    }

    @Override
    public String toString() {
        return symbol + type;
    }
}
