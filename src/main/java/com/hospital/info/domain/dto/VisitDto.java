package com.hospital.info.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VisitDto {
    private Long id;
    private String hospitalName;
    private String userName;
    private String diseaseName;
    private int amount;


}
