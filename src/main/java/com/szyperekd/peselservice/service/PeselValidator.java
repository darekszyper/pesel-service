package com.szyperekd.peselservice.service;

import com.szyperekd.peselservice.exception.InvalidPeselException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class PeselValidator {
    private static final int[] CONTROL_WEIGHTS = new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
    private static final Logger LOGGER = Logger.getLogger(PeselValidator.class.getName());

    public void assertIsValid(String pesel) throws InvalidPeselException {
        assertIsNotNull(pesel);
        assertIsLengthValid(pesel);
        assertIsOnlyDigits(pesel);
        assertIsControlDigitValid(pesel);
    }

    public void assertIsNotNull(String pesel) throws InvalidPeselException {
        LOGGER.info("assertIsNotNull(" + pesel + ")");
        boolean isNotNull = pesel != null;
        if (!isNotNull) {
            throw new InvalidPeselException("PESEL not provided");
        }
        LOGGER.info("assertIsNotNull(...) = " + isNotNull);
    }

    public void assertIsLengthValid(String pesel) throws InvalidPeselException {
        LOGGER.info("assertIsLengthValid(" + pesel + ")");
        boolean isLengthValid = pesel.length() == 11;
        if (!isLengthValid) {
            throw new InvalidPeselException("PESEL length is invalid");
        }
        LOGGER.info("assertIsNotNull(...) = " + isLengthValid);
    }

    public void assertIsOnlyDigits(String pesel) throws InvalidPeselException {
        LOGGER.info("assertIsOnlyDigits(" + pesel + ")");
        boolean isOnlyDigits = pesel.matches("[0-9]*");
        if (!isOnlyDigits) {
            throw new InvalidPeselException("PESEL contains forbidden character");
        }
        LOGGER.info("assertIsOnlyDigits(...) = " + isOnlyDigits);
    }

    public void assertIsControlDigitValid(String pesel) throws InvalidPeselException {
        LOGGER.info("assertIsControlDigitValid(" + pesel + ")");
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
        LOGGER.info("assertIsControlDigitValid(...) = " + isControlDigitValid);
    }
}
