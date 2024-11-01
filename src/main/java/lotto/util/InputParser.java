package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    private final static String DELIMITER = ",";
    private final static int LOTTO_PRICE = 1000;

    private InputParser() {
    }

    public static int parsePurchaseAmount(String input) {
        int amount = Integer.parseInt(input);
        return amount / LOTTO_PRICE;
    }

    public static List<Integer> parseWinningNumbers(String input) {
        String[] tokens = input.split(DELIMITER);
        return parseNumbers(tokens);

    }

    private static List<Integer> parseNumbers(String[] tokens) {
        return Arrays.stream(tokens)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}