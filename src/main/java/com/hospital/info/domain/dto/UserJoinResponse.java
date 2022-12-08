package com.hospital.info.domain.dto;

import com.hospital.info.domain.enums.UserGrade;
import lombok.Getter;

@Getter
public class UserJoinResponse {
    private String userName;
    private String email;
    private UserGrade grade;

    public UserJoinResponse(String userName, String email, UserGrade grade) {
        this.userName = userName;
        this.email = email;
        this.grade = grade;
    }
}
