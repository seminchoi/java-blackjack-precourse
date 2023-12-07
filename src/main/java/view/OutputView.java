package view;

import dto.BenefitDto;
import dto.ResultDto;
import dto.UserDto;

import java.util.List;

public class OutputView {
    public void printError(final Exception e) {
        System.out.printf("[ERROR] %s\n", e.getMessage());
    }

    public void printInitStatus(final List<UserDto> userDtos) {
        System.out.println();
        System.out.println("딜러와 pobi, jason에게 2장을 나누었습니다.");
        for (final UserDto userDto : userDtos) {
            System.out.println(userDto);
        }
    }

    public void printUserStatus(final UserDto userDto) {
        System.out.println();
        System.out.println(userDto);
    }

    public void printDealerTurnStatus() {
        System.out.println();
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public void printUsersResult(final List<ResultDto> resultDtos) {
        System.out.println();
        for (final ResultDto resultDto : resultDtos) {
            System.out.println(resultDto);
        }
    }

    public void printUsersBenefit(final List<BenefitDto> benefitDtos) {
        System.out.println();
        System.out.println("## 최종 수익");
        for (final BenefitDto benefitDto : benefitDtos) {
            System.out.println(benefitDto);
        }
    }
}
