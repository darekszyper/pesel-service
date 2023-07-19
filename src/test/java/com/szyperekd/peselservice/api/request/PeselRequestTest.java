package com.szyperekd.peselservice.api.request;

import com.szyperekd.peselservice.exception.InvalidPeselException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PeselRequestTest {


    @ParameterizedTest
    @MethodSource("provideCorrectPesels")
    @DisplayName("Should create valid object from correct pesel number")
    void shouldCreateValidObjetOfPesel(String example) {
        var pesel = new PeselRequest(example);
        assertNotNull(pesel);
    }

    private static Stream<Arguments> provideCorrectPesels() {
        return Stream.of(
                Arguments.of("77031167334"),
                Arguments.of("04242625931"),
                Arguments.of("92082683499"),
                Arguments.of("58883175997"),
                Arguments.of("58083175993"),
                Arguments.of("58283175999"),
                Arguments.of("58483175995"),
                Arguments.of("58683175991")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPesels")
    void should_throw_exception_if_given_pesel_is_invalid(String givenPesel) {
        assertThrows(InvalidPeselException.class, () -> new PeselRequest(givenPesel));
    }

    private static Stream<Arguments> provideInvalidPesels() {
        return Stream.of(
                Arguments.of("string"),
                Arguments.of("85122-96612"),
                Arguments.of("950510017595"),
                Arguments.of(""),
                Arguments.of("11111111111"),
                Arguments.of("95051001755"),
                Arguments.of("01016000019")
        );
    }


}