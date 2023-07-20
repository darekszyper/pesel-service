package com.szyperekd.peselservice.controller;

import com.szyperekd.peselservice.api.request.PeselRequest;
import com.szyperekd.peselservice.api.response.PeselResponse;
import com.szyperekd.peselservice.service.PeselDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PeselController {

    private final PeselDecoder peselDecoder;

    @PostMapping("/pesel")
    public ResponseEntity<PeselResponse> validateAndDecodePesel(@RequestBody PeselRequest peselRequest) {
        return ResponseEntity.ok(peselDecoder.retrieveData(peselRequest.pesel()));
    }
}