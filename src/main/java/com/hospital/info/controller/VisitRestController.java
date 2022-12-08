package com.hospital.info.controller;

import com.hospital.info.domain.dto.VisitCreateRequest;
import com.hospital.info.domain.dto.VisitResponse;
import com.hospital.info.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/visits")
public class VisitRestController {
    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<VisitResponse> createVisitLog(@RequestBody VisitCreateRequest visitCreateRequest, Authentication authentication) {
        String userName = authentication.getName();
        log.info("userName:{}", userName);
        VisitResponse visitInfo = visitService.createVisit(visitCreateRequest, userName);
        return ResponseEntity.ok().body(visitInfo);
    }
}
