package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 당첨_금액이_int_범위를_초과() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    // 예외를 던지는 상황을 테스트
                    assertThatThrownBy(() -> runException("8000", "1,2,3,4,5,6", "7"))
                            .isInstanceOf(ArithmeticException.class)
                            .hasMessageContaining(ERROR_MESSAGE);
                },
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }


    @Test
    void 예외_테스트_유효하지_않은_문자() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 예외_테스트_음수() {
        assertSimpleTest(() -> {
            runException("-1000");
            assertThat(output()).contains(ERROR_MESSAGE + " 음수는 입력할 수 없습니다.");
        });
    }

    @Test
    void 예외_테스트_1000원_미만() {
        assertSimpleTest(() -> {
            runException("500");
            assertThat(output()).contains(ERROR_MESSAGE + " 최소 금액은 1000원 이상이어야 합니다.");
        });
    }

    @Test
    void 예외_테스트_1000원_단위가_아닌_입력() {
        assertSimpleTest(() -> {
            runException("1500");
            assertThat(output()).contains(ERROR_MESSAGE + " 금액은 1000원 단위로 입력해야 합니다.");
        });
    }

    @Test
    void 예외_테스트_범위_초과() {
        assertSimpleTest(() -> {
            runException("2147483648"); // Integer 범위를 초과하는 값
            assertThat(output()).contains(ERROR_MESSAGE + " 입력값이 유효한 범위를 벗어났습니다.");
        });
    }

    @Test
    void 예외_테스트_잘못된_구분자() {
        assertSimpleTest(() -> {
            runException("8000", "1;2;3;4;5;6");
            assertThat(output()).contains(ERROR_MESSAGE + " 숫자는 쉼표(,)로 구분되어야 합니다.");
        });
    }

    @Test
    void 예외_테스트_보너스번호_중복() {
        assertSimpleTest(() -> {
            runException("8000", "1,2,3,4,5,6", "6");
            assertThat(output()).contains(ERROR_MESSAGE + " 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        });
    }

    @Test
    void 예외_테스트_로또번호_범위_초과() {
        // 로또 번호가 1~45 범위를 벗어날 때
        assertSimpleTest(() -> {
            runException("8000", "1,2,3,4,5,46");
            assertThat(output()).contains(ERROR_MESSAGE + " 로또 번호의 범위는 1~45입니다.");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
