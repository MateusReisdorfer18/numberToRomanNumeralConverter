import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mateusreisdorfer.Converter;

import java.util.stream.Stream;

public class ConverterTest {
    Converter converter;
    static Stream<Arguments> provideValidInputs() {
        return Stream.of(
                Arguments.of("1", "I"),
                Arguments.of("4", "IV"),
                Arguments.of("9", "IX"),
                Arguments.of("13", "XIII"),
                Arguments.of("29", "XXIX"),
                Arguments.of("44", "XLIV"),
                Arguments.of("58", "LVIII"),
                Arguments.of("77", "LXXVII"),
                Arguments.of("99", "XCIX"),
                Arguments.of("145", "CXLV"),
                Arguments.of("399", "CCCXCIX"),
                Arguments.of("444", "CDXLIV"),
                Arguments.of("589", "DLXXXIX"),
                Arguments.of("789", "DCCLXXXIX"),
                Arguments.of("999", "CMXCIX"),
                Arguments.of("1001", "MI"),
                Arguments.of("1456", "MCDLVI"),
                Arguments.of("1987", "MCMLXXXVII"),
                Arguments.of("2444", "MMCDXLIV"),
                Arguments.of("2789", "MMDCCLXXXIX"),
                Arguments.of("3000", "MMM"),
                Arguments.of("3333", "MMMCCCXXXIII"),
                Arguments.of("3888", "MMMDCCCLXXXVIII"),
                Arguments.of("3998", "MMMCMXCVIII"),
                Arguments.of("3999", "MMMCMXCIX")
        );
    }

    @BeforeEach
    void setUp() {
        converter = new Converter();
    }

    @ParameterizedTest
    @DisplayName("Test with valid inputs")
    @MethodSource("provideValidInputs")
    void test(String value, String romanNumeral) {
        Assertions.assertEquals(romanNumeral, converter.numberToRomanNumeral(value));
    }
}
