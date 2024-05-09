import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private static final List<LottoNumber> lottoNumbers = IntStream.rangeClosed(1, 45)
            .boxed()
            .map(LottoNumber::of)
            .collect(Collectors.toList());

    private final LottoNumbers value;

    public Lotto() {
        this.value = LottoNumbers.of(lottoNumbers);
    }

    public Lotto(LottoNumber... lottoNumbers) {
        this(LottoNumbers.of(Arrays.asList(lottoNumbers)));
    }

    public Lotto(Integer... numbers) {
        this(LottoNumbers.of(Arrays.stream(numbers)
                .map(LottoNumber::of)
                .collect(Collectors.toList())));
    }

    public Lotto(LottoNumbers value) {
        this.value = value;
    }

    public long countCommonLottoNumbers(LottoNumbers lottoNumbers) {
        return this.value.countCommonLottoNumbers(lottoNumbers);
    }

    public LottoNumbers numbers() {
        return this.value;
    }

    public int size() {
        return this.value.get().size();
    }
}
