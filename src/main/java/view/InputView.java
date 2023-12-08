package view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);
    public String readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입렵하세요.(쉼표 기준으로 분리)");
        return scanner.nextLine();
    }

    public double readPlayerBettingMoney(String name) {
        System.out.printf("%s의 배팅 금액은?\n", name);
        return readDouble();
    }

    private double readDouble() {
        String input = scanner.nextLine();
        try {
            return Double.parseDouble(input);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    public boolean readIntention(final String name) {
        System.out.printf("\n%s는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
        String input = scanner.nextLine();
        input = input.trim();
        if(input.equalsIgnoreCase("y")) {
            return true;
        }
        if(input.equalsIgnoreCase("n")) {
            return false;
        }
        throw new IllegalArgumentException("y또는 n으로만 입력해주세요");
    }
}
