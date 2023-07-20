package com.szyperekd.peselservice;

import java.time.DayOfWeek;
import java.time.LocalDate;

public record PeselResponse(Gender gender, LocalDate birthDate, DayOfWeek birthDayOfWeek) {
}
