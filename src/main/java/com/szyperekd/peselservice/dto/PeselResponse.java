package com.szyperekd.peselservice.dto;

import com.szyperekd.peselservice.core.Gender;

import java.time.DayOfWeek;
import java.time.LocalDate;

public record PeselResponse(Gender gender, LocalDate birthDate, DayOfWeek birthDayOfWeek) {
}
