package com.szyperekd.peselservice.service;

import com.szyperekd.peselservice.api.request.PeselRequest;
import com.szyperekd.peselservice.exception.InvalidPeselException;
import org.springframework.stereotype.Component;

@Component
public class PeselValidator {

    private static final int[] CONTROL_WEIGHTS = new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};

    public void assertIsValid(PeselRequest peselRequest) throws InvalidPeselException {
        assertIsLengthValid(peselRequest);
    }

    private void assertIsLengthValid(PeselRequest peselRequest) throws InvalidPeselException {
        if (peselRequest.pesel().length() != 11) {
            throw new InvalidPeselException("PESEL length is invalid");
        }
    }
}
