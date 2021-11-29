package calculator;

import calculator.domain.InputValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringAddCalculatorTest {
    @ParameterizedTest
    @DisplayName("입력값은 null 또는 빈문자일 경우 0을 출력한다.")
    @NullAndEmptySource
    public void splitAndSum_null_또는_빈문자(String inputStringValue) {
        assertThat(new StringAddCalculator().splitAndSum(new InputValue(inputStringValue))).isEqualTo(0);
    }

    @Test
    @DisplayName("숫자가 하나인 경우 숫자를 그대로 출력한다.")
    public void splitAndSum_숫자하나() throws Exception {
        assertThat(new StringAddCalculator().splitAndSum(new InputValue("1"))).isEqualTo(1);
    }

    @Test
    @DisplayName("컴마(,) 구분자 덧셈")
    public void splitAndSum_쉼표구분자() throws Exception {
        assertThat(new StringAddCalculator().splitAndSum(new InputValue("1,2"))).isEqualTo(3);
    }

    @Test
    @DisplayName("컴마(,) 또는 콜론(:) 구분자 덧셈")
    public void splitAndSum_쉼표_또는_콜론_구분자() throws Exception {
        assertThat(new StringAddCalculator().splitAndSum(new InputValue("1,2:3"))).isEqualTo(6);
    }

    @Test
    @DisplayName("“//”와 “\\n” 커스텀 구분자 덧셈")
    public void splitAndSum_custom_구분자() throws Exception {
        assertThat(new StringAddCalculator().splitAndSum(new InputValue("//;\n1;2;3"))).isEqualTo(6);
    }

    @Test
    @DisplayName("음수를 전달할 경우 RuntimeException 예외가 발생")
    public void splitAndSum_negative() throws Exception {
        assertThatThrownBy(() ->
                new StringAddCalculator().splitAndSum(new InputValue("-1,2,3"))
        ).isInstanceOf(RuntimeException.class);
    }
}