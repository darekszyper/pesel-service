package com.szyperekd.peselservice.api.response;

import java.time.DayOfWeek;
import java.time.LocalDate;

public record PeselResponse(Gender gender, LocalDate birthDate, DayOfWeek birthDayOfWeek) {
}
