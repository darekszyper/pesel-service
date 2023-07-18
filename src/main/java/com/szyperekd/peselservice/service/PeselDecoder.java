package com.szyperekd.peselservice.service;

import com.szyperekd.peselservice.api.request.PeselRequest;
import com.szyperekd.peselservice.api.response.Gender;
import com.szyperekd.peselservice.api.response.PeselResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PeselDecoder {

    public PeselResponse dummyResponse(PeselRequest peselRequest) {
        return new PeselResponse(LocalDate.of(2000, 1, 1), Gender.MALE);
    }
}
