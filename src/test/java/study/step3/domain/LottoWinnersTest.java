package study.step3.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("당첨 번호 세팅")
class LottoWinnersTest {

    public Set<Integer> createLottoNumber() {
        return IntStream.range(1,7)
                .boxed()
                .collect(Collectors.toSet());
    }

    @Test
    @DisplayName("당첨번호와 보너스번호 중복 테스트")
    void 당첨번호와_보너스번호_중복_테스트() {
        LottoNumbers winner = new LottoNumbers(createLottoNumber());
        LottoNumber bonus = new LottoNumber(1);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            new LottoWinners(winner, bonus);
        });
    }
}