package com.szyperekd.peselservice.api.request;

import com.szyperekd.peselservice.Pesel;
import com.szyperekd.peselservice.exception.InvalidPeselException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PeselTest {

    @ParameterizedTest
    @MethodSource("provideCorrectPesels")
    @DisplayName("Should create valid object from correct pesel number")
    void shouldCreateValidObjetOfPesel(String peselRequest) {
        // when
        var pesel = new Pesel(peselRequest);

        //then
        assertNotNull(pesel);
    }

    private static Stream<Arguments> provideCorrectPesels() {
        return Stream.of(
                Arguments.of("64020698643"),
                Arguments.of("78020218815"),
                Arguments.of("61121697421"),
                Arguments.of("56080527522"),
                Arguments.of("85060552692"),
                Arguments.of("51060426541"),
                Arguments.of("92100226943"),
                Arguments.of("77122972353")
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Should throw InvalidPeselException when PESEL is null")
    void shouldThrowInvalidPeselExceptionWhenPeselIsNull(String peselRequest) {
        assertThrows(InvalidPeselException.class, () -> new Pesel(peselRequest));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPesels")
    @DisplayName("Should throw InvalidPeselException object when PESEL is invalid")
    void shouldThrowInvalidPeselExceptionWhenPeselIsInvalid(String peselRequest) {
        assertThrows(InvalidPeselException.class, () -> new Pesel(peselRequest));
    }

    private static Stream<Arguments> provideInvalidPesels() {
        return Stream.of(
                Arguments.of("string"),
                Arguments.of("85122-96612"),
                Arguments.of("950510501759"),
                Arguments.of(""),
                Arguments.of("\n"),
                Arguments.of("11111111111"),
                Arguments.of("95051001755"),
                Arguments.of("           "),
                Arguments.of("04562266388"),
                Arguments.of("04514566382"),
                Arguments.of("01016000019")
        );
    }
}