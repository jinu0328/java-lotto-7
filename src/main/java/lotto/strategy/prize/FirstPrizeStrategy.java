package lotto.strategy.prize;

import lotto.model.Lotto;
import lotto.model.WinningNumbers;

public class FirstPrizeStrategy implements PrizeStrategy {
    private static final int PRIZE_AMOUNT = 2_000_000_000;

    @Override
    public boolean isWinning(Lotto lotto, WinningNumbers winningNumbers) {
        int matchCount = winningNumbers.getMatchCount(lotto);
        return matchCount == 6;
    }

    @Override
    public int getPrizeAmount() {
        return PRIZE_AMOUNT;
    }
}
