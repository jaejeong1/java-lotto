package lotto.game.domain.vo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.game.exception.IllegalBallNumberException;
import lotto.io.domain.vo.InputText;
import lotto.io.exception.IllegalInputTextException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BallTest {

	@DisplayName("4-1-2-1-1.validate() : 1~45의 자연수 유효성 체크")
	@ParameterizedTest(name = "{index} - text:[{0}], exceptedNotThrownException:{1}")
	@Order(1)
	@CsvSource(value = {"0;false", "1;true", "45;true", "46;false", "one;false", "fortyFive;false"}, delimiter = ';')
	void generate(String text, boolean exceptedNotThrownException) throws IllegalInputTextException {
		//given
		InputText inputText = InputText.generate(text);

		//when
		if (exceptedNotThrownException) {
			//then - not throw any Exception
			assertThatCode(() -> Ball.validateGenerate(inputText)).doesNotThrowAnyException();
			return;
		}
		//then - throw NumberFormatException
		assertThatThrownBy(() -> Ball.validateGenerate(inputText))
			.isInstanceOfAny(IllegalBallNumberException.class);
	}

	@DisplayName("4-1-2-1-2.`generate()` : Ball 생성")
	@ParameterizedTest(name = "{index} - text:[{0}], expectedNumber:{1}, exceptedNotThrownException:{2}")
	@Order(2)
	@CsvSource(value = {"0;0;false", "1;1;true", "45;45;true", "46;46;false", "one;1;false",
		"fortyFive;45;false"}, delimiter = ';')
	void generate(String text, int expectedNumber, boolean exceptedNotThrownException) throws
			IllegalInputTextException, IllegalBallNumberException {
		//given
		InputText inputText = InputText.generate(text);

		//when
		if (exceptedNotThrownException) {
			//then -
			assertThat(Ball.generate(inputText).number()).isEqualTo(expectedNumber);
			return;
		}
		//then - throw NumberFormatException
		assertThatThrownBy(() -> Ball.generate(inputText).number()).isInstanceOf(IllegalBallNumberException.class);
	}

	@DisplayName("4-1-2-1-3.`equals()` : 등가성 비교")
	@ParameterizedTest(name = "{index} - text:[{0}], expectedNumberText:[{1}], exceptedIsEquals:{2}")
	@Order(3)
	@CsvSource(value = {"1;45;false", "01;001;true", "45;45;true",
			"45;1;false", "01;1;true", "20;20;true"}, delimiter = ';')
	void equals(String text, String expectedNumberText, boolean exceptedIsEquals) throws
			IllegalInputTextException, IllegalBallNumberException {
		//given
		InputText inputText = InputText.generate(text);
		InputText inputTextExpectedNumber = InputText.generate(expectedNumberText);

		Ball ball = Ball.generate(inputText);
		Ball expectedBall = Ball.generate(inputTextExpectedNumber);

		//when

		//then
		assertThat(ball.equals(expectedBall)).isEqualTo(exceptedIsEquals);
	}
}