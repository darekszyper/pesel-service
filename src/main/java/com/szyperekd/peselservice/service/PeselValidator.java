package com.szyperekd.peselservice.service;

import com.szyperekd.peselservice.api.request.PeselRequest;
import com.szyperekd.peselservice.exception.InvalidPeselException;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class PeselValidator {

    private static final int[] CONTROL_WEIGHTS = new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
    private static final Logger LOGGER = Logger.getLogger(PeselValidator.class.getName());
    private static final Logger ERROR_LOGGER = Logger.getLogger(InvalidPeselException.class.getName());

    public void assertIsValid(PeselRequest peselRequest) throws InvalidPeselException {
        assertIsNotNull(peselRequest);
        assertIsLengthValid(peselRequest);
        assertIsOnlyDigits(peselRequest);
        assertIsControlDigitValid(peselRequest);
        //assertIsBirthDateValid(peselRequest);
    }

    private void assertIsNotNull(PeselRequest peselRequest) throws InvalidPeselException {
        LOGGER.info("assertIsNotNull(" + peselRequest.pesel() + ")");
        boolean isNotNull = peselRequest.pesel() != null;
        if (!isNotNull) {
            ERROR_LOGGER.warning("PESEL is null");
            throw new InvalidPeselException("PESEL not provided");
        }
        LOGGER.info("assertIsNotNull(...) = " + isNotNull);
    }

    private void assertIsLengthValid(PeselRequest peselRequest) throws InvalidPeselException {
        LOGGER.info("assertIsLengthValid(" + peselRequest.pesel() + ")");
        boolean isLengthValid = peselRequest.pesel().length() == 11;
        if (!isLengthValid) {
            ERROR_LOGGER.warning("PESEL length is invalid");
            throw new InvalidPeselException("PESEL length is invalid");
        }
        LOGGER.info("assertIsNotNull(...) = " + isLengthValid);
    }

    private void assertIsOnlyDigits(PeselRequest peselRequest) throws InvalidPeselException {
        LOGGER.info("assertIsOnlyDigits(" + peselRequest.pesel() + ")");
        boolean isOnlyDigits = peselRequest.pesel().matches("[0-9]*");
        if (!isOnlyDigits) {
            ERROR_LOGGER.warning("assertIsOnlyDigits(...) = " + isOnlyDigits);
            throw new InvalidPeselException("PESEL contains invalid characters");
        }
        LOGGER.info("assertIsOnlyDigits(...) = " + isOnlyDigits);
    }

    private void assertIsControlDigitValid(PeselRequest peselRequest) throws InvalidPeselException {
        LOGGER.info("assertIsControlDigitValid(" + peselRequest.pesel() + ")");
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int multipliedByWeight = CONTROL_WEIGHTS[i] * Character.getNumericValue(peselRequest.pesel().charAt(i));
            int multipliedByWeightLastDigit = multipliedByWeight % 10;
            sum = sum + multipliedByWeightLastDigit;
        }
        int calculatedControlDigit = 10 - (sum % 10);
        boolean isControlDigitValid =
                calculatedControlDigit == Character.getNumericValue(peselRequest.pesel().charAt(10));
        if (!isControlDigitValid) {
            ERROR_LOGGER.warning("assertIsControlDigitValid(...) = " + isControlDigitValid);
            throw new InvalidPeselException("Control Digit is invalid");
        }
        LOGGER.info("assertIsControlDigitValid(...) = " + isControlDigitValid);
    }

//    static void assertIsBirthDateValid(String pesel) throws InvalidPeselException {
//        if (!isBirthDateValid(pesel)) {
//            throw new InvalidPeselException("PESEL birth date is invalid");
//        }
//    }
//
//    private static boolean isBirthDateValid(String pesel) {
//        try {
//            PESEL_DECODER.decodeBirthDate(pesel);
//            return true;
//        } catch (DateTimeException e) {
//            return false;
//        }
//    }
}
