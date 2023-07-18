package com.szyperekd.peselservice.api.response;

import java.time.LocalDate;

public record PeselResponse(LocalDate birthDate, Gender gender) {
}
