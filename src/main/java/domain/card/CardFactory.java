package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 트럼프 카드 전체 생성을 담당하는 객체
 */
public class CardFactory {
    public static Queue<Card> create() {
        List<Card> cards = new LinkedList<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        Collections.shuffle(cards);
        return new LinkedList<>(cards);
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }
}
