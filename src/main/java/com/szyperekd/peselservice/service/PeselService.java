package com.szyperekd.peselservice.service;

import com.szyperekd.peselservice.dto.PeselRequest;
import com.szyperekd.peselservice.dto.PeselResponse;
import com.szyperekd.peselservice.mapper.PeselMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeselService {

    private final PeselMapper peselMapper;
    private final PeselDecoder peselDecoder;
    public PeselResponse validateAndDecodePesel(PeselRequest peselRequest) {
        Pesel pesel = peselMapper.mapPeselRequestToPesel(peselRequest);
        Pesel decodedPesel = peselDecoder.retrieveData(pesel);
        return peselMapper.mapPeselToPeselResponse(decodedPesel);
    }
}
