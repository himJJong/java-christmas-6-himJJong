package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DayTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32})
    @DisplayName("12월일 때 1~31의 숫자가 아닌 경우 예외를 처리한다.")
    void testInputDayWrongNumber(int number) {
        assertThatThrownBy(() -> new Day(number))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
