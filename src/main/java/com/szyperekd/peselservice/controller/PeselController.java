package com.szyperekd.peselservice.controller;

import com.szyperekd.peselservice.dto.PeselRequest;
import com.szyperekd.peselservice.dto.PeselResponse;
import com.szyperekd.peselservice.service.PeselService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public PeselResponse validateAndDecodePesel(@RequestBody PeselRequest peselRequest) {
        return peselService.validateAndDecodePesel(peselRequest);
    }
}