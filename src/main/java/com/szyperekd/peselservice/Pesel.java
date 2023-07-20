package com.szyperekd.peselservice;

import com.szyperekd.peselservice.service.PeselValidator;

public record Pesel(String pesel) {

    private static final PeselValidator peselValidator = new PeselValidator();

    public Pesel {
        peselValidator.isValid(pesel);
    }
}