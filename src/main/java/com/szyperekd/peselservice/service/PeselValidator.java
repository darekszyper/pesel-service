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
    private static final Logger ERROR_LOGGER = Logger.getLogger(InvalidPeselException.class.getName());

    public void assertIsValid(String pesel) throws InvalidPeselException {
        assertIsNotNull(pesel);
        assertIsLengthValid(pesel);
        assertIsOnlyDigits(pesel);
        assertIsControlDigitValid(pesel);
    }

    private void assertIsNotNull(String pesel) throws InvalidPeselException {
        LOGGER.info("assertIsNotNull(" + pesel + ")");
        boolean isNotNull = pesel != null;
        if (!isNotNull) {
            ERROR_LOGGER.warning("PESEL is null");
            throw new InvalidPeselException("PESEL not provided");
        }
        LOGGER.info("assertIsNotNull(...) = " + isNotNull);
    }

    private void assertIsLengthValid(String pesel) throws InvalidPeselException {
        LOGGER.info("assertIsLengthValid(" + pesel + ")");
        boolean isLengthValid = pesel.length() == 11;
        if (!isLengthValid) {
            ERROR_LOGGER.warning("PESEL length is invalid");
            throw new InvalidPeselException("PESEL length is invalid");
        }
        LOGGER.info("assertIsNotNull(...) = " + isLengthValid);
    }

    private void assertIsOnlyDigits(String pesel) throws InvalidPeselException {
        LOGGER.info("assertIsOnlyDigits(" + pesel + ")");
        boolean isOnlyDigits = pesel.matches("[0-9]*");
        if (!isOnlyDigits) {
            ERROR_LOGGER.warning("assertIsOnlyDigits(...) = " + isOnlyDigits);
            throw new InvalidPeselException("PESEL contains forbidden character");
        }
        LOGGER.info("assertIsOnlyDigits(...) = " + isOnlyDigits);
    }

    private void assertIsControlDigitValid(String pesel) throws InvalidPeselException {
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
            ERROR_LOGGER.warning("assertIsControlDigitValid(...) = " + isControlDigitValid);
            throw new InvalidPeselException("Control Digit is invalid");
        }
        LOGGER.info("assertIsControlDigitValid(...) = " + isControlDigitValid);
    }
}
