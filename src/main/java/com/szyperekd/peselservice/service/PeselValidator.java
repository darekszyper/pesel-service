package com.szyperekd.peselservice.service;


import com.szyperekd.peselservice.exception.InvalidPeselException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.logging.Logger;

public class PeselValidator {
    private static final int[] CONTROL_WEIGHTS = new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
    private static final Logger LOGGER = Logger.getLogger(PeselValidator.class.getName());

    public void isValid(String pesel) {
        isNotNullOrBlank(pesel);
        isLengthValid(pesel);
        isOnlyDigits(pesel);
        isControlDigitValid(pesel);
        isBirthDateValid(pesel);
    }

    private void isNotNullOrBlank(String pesel) {
        LOGGER.info("isNotNullOrBlank(" + pesel + ")");
        boolean isNotNullOrBlank = pesel != null && !pesel.isBlank();
        if (!isNotNullOrBlank) {
            throw new InvalidPeselException("PESEL not provided");
        }
        LOGGER.info("isNotNullOrBlank(...) = " + isNotNullOrBlank);
    }

    private void isLengthValid(String pesel) {
        LOGGER.info("isLengthValid(" + pesel + ")");
        boolean isLengthValid = pesel.length() == 11;
        if (!isLengthValid) {
            throw new InvalidPeselException("PESEL length is invalid");
        }
        LOGGER.info("isLengthValid(...) = " + isLengthValid);
    }

    private void isOnlyDigits(String pesel) {
        LOGGER.info("isOnlyDigits(" + pesel + ")");
        boolean isOnlyDigits = pesel.matches("[0-9]*");
        if (!isOnlyDigits) {
            throw new InvalidPeselException("PESEL contains forbidden character");
        }
        LOGGER.info("isOnlyDigits(...) = " + isOnlyDigits);
    }

    private void isControlDigitValid(String pesel) {
        LOGGER.info("isControlDigitValid(" + pesel + ")");
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int multipliedByWeight = CONTROL_WEIGHTS[i] * Character.getNumericValue(pesel.charAt(i));
            int multipliedByWeightLastDigit = multipliedByWeight % 10;
            sum = sum + multipliedByWeightLastDigit;
        }
        int calculatedControlDigit = 10 - (sum % 10);
        boolean isControlDigitValid =
                calculatedControlDigit == Character.getNumericValue(pesel.charAt(10));
        if (!isControlDigitValid) {
            throw new InvalidPeselException("Control Digit is invalid");
        }
        LOGGER.info("isControlDigitValid(...) = " + isControlDigitValid);
    }

    private void isBirthDateValid(String pesel) {
        try {
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
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new InvalidPeselException("Birth Date is invalid");
        }
    }
}
