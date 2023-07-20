package com.szyperekd.peselservice.service;

import com.szyperekd.peselservice.Pesel;
import com.szyperekd.peselservice.PeselResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeselService {

    private final PeselDecoder peselDecoder;
    public PeselResponse validateAndDecodePesel(String peselRequest) {
        Pesel pesel = new Pesel(peselRequest);
        return peselDecoder.retrieveData(pesel);
    }
}
