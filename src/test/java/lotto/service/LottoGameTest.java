package lotto.service;

import lotto.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoGameTest {
    private LottoGame lottoGame;

    @BeforeEach
    void setUp() {
        lottoGame = new LottoGame();
    }

    @Test
    @DisplayName("코인 생성 테스트")
    void createCoin() {
        assertThat(lottoGame.changeCoin(14500)).isEqualTo(14);
    }

    @Test
    @DisplayName("로또 구매 개수 테스트")
    void createLotto() {
        assertThat(lottoGame.createLottoNumber(15).buyLottoCount()).isEqualTo(15);
    }

    @Test
    @DisplayName("당첨 번호 생성 길이 체크 테스트")
    void createWinnerNumbers() {
        assertThat(lottoGame.createWinnerNumbers("1,2,3,4,5,6", 7).winnerNumberLength()).isEqualTo(6);
    }

    @Test
    @DisplayName("등수별 당첨 통합 테스트")
    void winnerSecondIntegration() {
        LottoStatistics lottoStatistics = lottoGame.statistics(
                new Lottos(asList(
                        new Lotto(asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))),
                        new Lotto(asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(7))),
                        new Lotto(asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(8))),
                        new Lotto(asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(11), LottoNumber.of(10))),
                        new Lotto(asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(12), LottoNumber.of(11), LottoNumber.of(10))),
                        new Lotto(asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(13), LottoNumber.of(12), LottoNumber.of(11), LottoNumber.of(10)))
                )),
                lottoGame.createWinnerNumbers("1,2,3,4,5,7", 6)
        );

        assertThat(lottoStatistics.getMatchResult().get(WinnerAmount.FIRST)).isEqualTo(1);
        assertThat(lottoStatistics.getMatchResult().get(WinnerAmount.SECOND)).isEqualTo(1);
        assertThat(lottoStatistics.getMatchResult().get(WinnerAmount.THIRD)).isEqualTo(1);
        assertThat(lottoStatistics.getMatchResult().get(WinnerAmount.FORTH)).isEqualTo(1);
        assertThat(lottoStatistics.getMatchResult().get(WinnerAmount.FIFTH)).isEqualTo(1);
        assertThat(lottoStatistics.getMatchResult().get(WinnerAmount.ZERO)).isEqualTo(1);
    }

}