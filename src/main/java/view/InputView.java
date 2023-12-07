package view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);
    public String readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입렵하세요.(쉼표 기준으로 분리)");
        return scanner.next();
    }

    public double readPlayerBettingMoney(String name) {
        System.out.printf("%s의 배팅 금액은?\n", name);
        return readDouble();
    }

    private double readDouble() {
        String input = scanner.next();
        try {
            return Double.parseDouble(input);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }
}
