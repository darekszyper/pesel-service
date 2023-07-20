package com.szyperekd.peselservice.service;

import com.szyperekd.peselservice.api.response.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static com.szyperekd.peselservice.api.response.Gender.FEMALE;
import static com.szyperekd.peselservice.api.response.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PeselDecoderTest {

    private PeselDecoder underTest;

    @BeforeEach
    void setUp() {
        underTest = new PeselDecoder();
    }

    @ParameterizedTest
    @MethodSource("providePeselsFromEachCentury")
    @DisplayName("Should return date for PESEL from each century")
    void shouldReturnCorrectDateForPeselFromEachCentury(String peselRequest, LocalDate expectedResult) {
        // when
        LocalDate returnedDate = underTest.decodeBirthDate(peselRequest);

        //then
        assertEquals(expectedResult, returnedDate);
    }

    public static Stream<Arguments> providePeselsFromEachCentury() {
        return Stream.of(
                Arguments.of("77031167334", LocalDate.of(1977, 3, 11)),
                Arguments.of("04242625931", LocalDate.of(2004, 4, 26)),
                Arguments.of("92082683499", LocalDate.of(1992, 8, 26)),
                Arguments.of("58883175997", LocalDate.of(1858, 8, 31)),
                Arguments.of("58083175993", LocalDate.of(1958, 8, 31)),
                Arguments.of("58283175999", LocalDate.of(2058, 8, 31)),
                Arguments.of("58483175995", LocalDate.of(2158, 8, 31)),
                Arguments.of("58683175991", LocalDate.of(2258, 8, 31))
        );
    }

    @ParameterizedTest
    @MethodSource("providePeselsAndGender")
    @DisplayName("Should return gender from PESEL")
    void shouldReturnGenderFromPesel(String peselRequest, Gender expectedResult) {
        // when
        Gender returnedGender = underTest.decodeGender(peselRequest);

        // then
        assertEquals(expectedResult, returnedGender);
    }

    private static Stream<Arguments> providePeselsAndGender() {
        return Stream.of(
                Arguments.of("52060922143", FEMALE),
                Arguments.of("07262856143", FEMALE),
                Arguments.of("73052894639", MALE),
                Arguments.of("91092643118", MALE)

        );
    }
}