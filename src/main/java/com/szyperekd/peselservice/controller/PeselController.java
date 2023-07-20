package com.szyperekd.peselservice.controller;

import com.szyperekd.peselservice.PeselResponse;
import com.szyperekd.peselservice.service.PeselService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.szyperekd.peselservice.controller.PeselController.BASE_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(BASE_URL)
public class PeselController {

    public final static String BASE_URL = "/api/v1";
    public final static String PESEL_SERVICE = "/pesel";

    private final PeselService peselService;

    @PostMapping(PESEL_SERVICE)
    public ResponseEntity<PeselResponse> validateAndDecodePesel(@RequestParam(required = false) String peselRequest) {
        return ResponseEntity.ok(peselService.validateAndDecodePesel(peselRequest));
    }
}