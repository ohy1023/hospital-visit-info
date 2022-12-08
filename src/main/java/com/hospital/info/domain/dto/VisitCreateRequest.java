package com.hospital.info.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VisitCreateRequest {
    private Long hospitalId;
    private String disease;
    private int amount;
}