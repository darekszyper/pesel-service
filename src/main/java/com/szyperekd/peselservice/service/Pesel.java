package com.szyperekd.peselservice.service;

import com.szyperekd.peselservice.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Getter
@Setter
public class Pesel {
    private String pesel;
    private Gender gender;
    private LocalDate birthDate;
    private DayOfWeek birthDayOfWeek;

    public Pesel(String pesel) {
        this(new PeselValidator(), pesel);
    }

    private Pesel(PeselValidator peselValidator, String pesel) {
        peselValidator.isValid(pesel);
        this.pesel = pesel;
    }
}