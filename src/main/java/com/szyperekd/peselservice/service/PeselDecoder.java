package com.szyperekd.peselservice.service;

import com.szyperekd.peselservice.api.response.Gender;
import com.szyperekd.peselservice.api.response.PeselResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.logging.Logger;

@Component
public class PeselDecoder {

    private static final Logger LOGGER = Logger.getLogger(PeselDecoder.class.getName());

    public PeselResponse retrieveData(String pesel) {
        LOGGER.info("retrieveData(" + pesel + ")");
        return new PeselResponse(decodeBirthDate(pesel), decodeGender(pesel));
    }

    LocalDate decodeBirthDate(String pesel) {
        LOGGER.info("decodeBirthDate(" + pesel + ")");
        int year = 1900;
        int month = Integer.parseInt(pesel.substring(2, 4));
        int day = Integer.parseInt(pesel.substring(4, 6));

        int monthFirstDigit = Character.getNumericValue(pesel.charAt(2));
        switch (monthFirstDigit) {
            case 8, 9 -> {
                month -= 80;
                year = 1800;
            }
            case 2, 3 -> {
                month -= 20;
                year = 2000;
            }
            case 4, 5 -> {
                month -= 40;
                year = 2100;
            }
            case 6, 7 -> {
                month -= 60;
                year = 2200;
            }
        }

        year += Integer.parseInt(pesel.substring(0, 2));
        LocalDate birthDate = LocalDate.of(year, month, day);
        LOGGER.info("decodeBirthDate(...) = " + birthDate);
        return birthDate;
    }

    Gender decodeGender(String pesel) {
        LOGGER.info("decodeGender(" + pesel + ")");
        int genderDigit = Character.getNumericValue(pesel.charAt(9));
        Gender gender = genderDigit % 2 == 0 ? Gender.FEMALE : Gender.MALE;
        LOGGER.info("decodeGender(...) = " + gender);
        return gender;
    }
}