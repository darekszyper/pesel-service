package com.szyperekd.peselservice.controller;

import com.szyperekd.peselservice.api.request.PeselRequest;
import com.szyperekd.peselservice.exception.InvalidPeselException;
import com.szyperekd.peselservice.service.PeselService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PeselController {

    //TODO: use ControllerAdvisor
    private final PeselService peselService;

    @PostMapping("/pesel")
    public ResponseEntity<?> validateAndDecodePesel(@RequestBody @Valid PeselRequest peselRequest) {
        try {
            return ResponseEntity.ok(peselService.validateAndDecodePesel(peselRequest));
        } catch (InvalidPeselException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}