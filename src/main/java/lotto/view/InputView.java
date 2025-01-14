package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String COST_INPUT_MESSAGE = "구입금액을 입력해주세요.";
    private static final String WINNING_NUMBERS_INPUT_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 번호를 입력해 주세요.";

    public String getCost() {
        System.out.println(COST_INPUT_MESSAGE);
        return Console.readLine();
    }

    public String getWinningNumbers() {
        System.out.println(WINNING_NUMBERS_INPUT_MESSAGE);
        return Console.readLine();
    }

    public String getBonusNumber() {
        System.out.println(BONUS_NUMBER_INPUT_MESSAGE);
        return Console.readLine();
    }
}
