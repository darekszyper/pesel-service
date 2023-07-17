package com.szyperekd.peselservice.api.request;

import jakarta.validation.constraints.Size;


public class PeselRequest {

    @Size(min = 11, message = "PESEL is too short")
    @Size(max = 11, message = "PESEL is too long")
    private String pesel;

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
