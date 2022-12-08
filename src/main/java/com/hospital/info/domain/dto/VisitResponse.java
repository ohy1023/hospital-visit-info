package com.hospital.info.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VisitResponse {
    private String hospitalName;
    private String userName;
    private String disease;
    private int amount;

    @Builder
    public VisitResponse(String hospitalName, String userName, String disease, int amount) {
        this.hospitalName = hospitalName;
        this.userName = userName;
        this.disease = disease;
        this.amount = amount;
    }
}
