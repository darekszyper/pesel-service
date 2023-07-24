package com.szyperekd.peselservice.service;

import com.szyperekd.peselservice.core.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
@Slf4j
public class PeselDecoder {


    Pesel retrieveData(Pesel peselObject) {
        String pesel = peselObject.getPesel();

        LocalDate birthDate = decodeBirthDate(pesel);
        peselObject.setBirthDate(birthDate);

        DayOfWeek birthDayOfWeek = birthDate.getDayOfWeek();
        peselObject.setBirthDayOfWeek(birthDayOfWeek);

        Gender gender = decodeGender(pesel);
        peselObject.setGender(gender);

        return peselObject;
    }

    LocalDate decodeBirthDate(String pesel) {
        log.info("decodeBirthDate(" + pesel + ")");
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
        log.info("decodeBirthDate(...) = " + birthDate);
        return birthDate;
    }

    Gender decodeGender(String pesel) {
        log.info("decodeGender(" + pesel + ")");
        int genderDigit = Character.getNumericValue(pesel.charAt(9));
        Gender gender = genderDigit % 2 == 0 ? Gender.FEMALE : Gender.MALE;
        log.info("decodeGender(...) = " + gender);
        return gender;
    }
}
