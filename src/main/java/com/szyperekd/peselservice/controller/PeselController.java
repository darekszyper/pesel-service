package com.szyperekd.peselservice.controller;

import com.szyperekd.peselservice.api.request.PeselRequest;
import com.szyperekd.peselservice.api.response.PeselResponse;
import com.szyperekd.peselservice.service.PeselService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PeselController {

    private final PeselService peselService;

    public PeselController(PeselService peselService) {
        this.peselService = peselService;
    }

    @PostMapping("/pesel")
    public ResponseEntity<PeselResponse> validatePesel(@RequestBody @Valid PeselRequest peselRequest) {
        return ResponseEntity.ok(peselService.validatePesel(peselRequest));
    }
}

