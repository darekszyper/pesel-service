package com.szyperekd.peselservice.service;

import com.szyperekd.peselservice.api.request.PeselRequest;
import com.szyperekd.peselservice.api.response.PeselResponse;
import com.szyperekd.peselservice.exception.InvalidPeselException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeselService {

    private final PeselValidator peselValidator;
    private final PeselDecoder peselDecoder;

    public PeselResponse validateAndDecodePesel(PeselRequest peselRequest) throws InvalidPeselException {
        String pesel = peselRequest.pesel();
        peselValidator.assertIsValid(pesel);
        return peselDecoder.retrieveData(pesel);
    }
}
