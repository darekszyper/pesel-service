package com.szyperekd.peselservice.api.request;

import com.szyperekd.peselservice.service.PeselDecoder;
import com.szyperekd.peselservice.service.PeselValidator;

public record PeselRequest(String pesel) {

    private static final PeselValidator peselValidator = new PeselValidator(new PeselDecoder());

    public PeselRequest {
        peselValidator.assertIsValid(pesel);
    }
}
