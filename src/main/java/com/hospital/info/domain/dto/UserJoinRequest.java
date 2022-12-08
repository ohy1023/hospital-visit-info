package com.hospital.info.domain.dto;

import com.hospital.info.domain.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;

    public Users toEntity(String password) {
        return Users.builder()
                .userName(this.userName)
                .password(password)
                .emailAddress(this.email)
                .build();
    }

}
