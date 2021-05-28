package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTest {
	List<LottoNumber> lottoNumbers;

	@BeforeEach
	void setUp() {
		lottoNumbers = new ArrayList<>();
		lottoNumbers.add(LottoNumber.of(1));
		lottoNumbers.add(LottoNumber.of(2));
		lottoNumbers.add(LottoNumber.of(3));
		lottoNumbers.add(LottoNumber.of(4));
		lottoNumbers.add(LottoNumber.of(5));
		lottoNumbers.add(LottoNumber.of(6));
	}

	@Test
	@DisplayName("로또 생성 테스트")
	public void createLottoTest() {
		Lotto lotto = new Lotto(lottoNumbers);
		assertThat(lotto).isNotNull();
	}

	@Test
	@DisplayName("로또 6개 숫자 이상의 생성 시 에러 발생 테스트")
	public void createLotto_6자리_로또_숫자가_아닌_경우() {
		lottoNumbers.add(LottoNumber.of(7));
		assertThatThrownBy(() -> new Lotto(lottoNumbers))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("로또 중복 번호 체크 에러 테스트")
	public void createLotto_중복번호가_입력되_에러_발생하는_경우(){
		List<LottoNumber> duplicatedLotto = new ArrayList<>();
		duplicatedLotto.add(LottoNumber.of(1));
		duplicatedLotto.add(LottoNumber.of(1));
		duplicatedLotto.add(LottoNumber.of(3));
		duplicatedLotto.add(LottoNumber.of(4));
		duplicatedLotto.add(LottoNumber.of(5));
		duplicatedLotto.add(LottoNumber.of(6));
		assertThatThrownBy(() -> new Lotto(duplicatedLotto))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("로또 가격 가져오기 테스트")
	public void getLottoPriceTest() {
		assertThat(Lotto.LOTTO_PRICE).isEqualTo(1000);
	}

	@Test
	@DisplayName("같은 로또인지 확인하는 테스트")
	public void equalsLottoTest() {
		Lotto sourceLotto = new Lotto(lottoNumbers);
		Lotto winningLotto = new Lotto(lottoNumbers);
		assertThat(sourceLotto).isNotNull();
		assertThat(winningLotto).isNotNull();
		assertThat(sourceLotto.equals(winningLotto)).isEqualTo(true);
	}

	@Test
	@DisplayName("같지 않는 로또인지 확인하는 테스트")
	public void notEqualsLottoTest() {
		Lotto sourceLotto = new Lotto(lottoNumbers);
		List<LottoNumber> anotherLottoNumbers = new ArrayList<>();
		anotherLottoNumbers.add(LottoNumber.of(1));
		anotherLottoNumbers.add(LottoNumber.of(2));
		anotherLottoNumbers.add(LottoNumber.of(3));
		anotherLottoNumbers.add(LottoNumber.of(4));
		anotherLottoNumbers.add(LottoNumber.of(5));
		anotherLottoNumbers.add(LottoNumber.of(7));
		Lotto winningLotto = new Lotto(anotherLottoNumbers);
		assertThat(sourceLotto).isNotNull();
		assertThat(winningLotto).isNotNull();
		assertThat(sourceLotto.equals(winningLotto)).isEqualTo(false);
	}

	@ParameterizedTest
	@CsvSource(value = {"1:2:3:4:5:6:6", "1:2:3:4:5:7:5", "1:2:3:4:7:8:4", "1:2:3:7:8:9:3", "1:2:7:8:9:10:2",
		"1:7:8:9:10:11:1", "7:8:9:10:11:12:0"}, delimiter = ':')
	@DisplayName("로또 같은 개수가 몇개인지 조회하는 테스트")
	public void matchesCount(int firstNumber, int secondNumber, int thirdNumber, int fourthNumber, int fifthNumber,
		int sixthNumber, int matches) {
		Lotto sourceLotto = new Lotto(lottoNumbers);
		List<LottoNumber> anotherLottoNumbers = new ArrayList<>();
		anotherLottoNumbers.add(LottoNumber.of(firstNumber));
		anotherLottoNumbers.add(LottoNumber.of(secondNumber));
		anotherLottoNumbers.add(LottoNumber.of(thirdNumber));
		anotherLottoNumbers.add(LottoNumber.of(fourthNumber));
		anotherLottoNumbers.add(LottoNumber.of(fifthNumber));
		anotherLottoNumbers.add(LottoNumber.of(sixthNumber));
		Lotto winningLotto = new Lotto(anotherLottoNumbers);
		assertThat(winningLotto.getMatchesCount(sourceLotto)).isEqualTo(matches);
	}

	@Test
	@DisplayName("로또에 로또 번호가 포함되어있는지 확인하는 테스트")
	public void containLottoNumber() {
		Lotto lotto = new Lotto(lottoNumbers);
		assertThat(lotto.containNumber(LottoNumber.of(6))).isEqualTo(true);
		assertThat(lotto.containNumber(LottoNumber.of(7))).isEqualTo(false);
	}

}