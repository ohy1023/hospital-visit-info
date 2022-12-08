package com.hospital.info.domain.dto;

import com.hospital.info.domain.enums.UserGrade;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private UserGrade grade;

    @Builder
    public UserDto(Long id, String userName, String password, String email, UserGrade grade) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.grade = grade;
    }
}
