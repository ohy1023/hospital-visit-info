package com.hospital.info.service;

import com.hospital.info.domain.dto.UserDto;
import com.hospital.info.domain.dto.UserJoinRequest;
import com.hospital.info.domain.dto.UserLoginRequest;
import com.hospital.info.domain.entity.Users;
import com.hospital.info.repository.UserRepository;
import com.hospital.info.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret}")
    private String secretKey;
    private final Long expiredTimeMs = 1000 * 60 * 60L;

    public UserDto registerUser(UserJoinRequest userJoinRequest) {
        userRepository.findByUserName(userJoinRequest.getUserName())
                .ifPresent((user) -> {
                    throw new RuntimeException("중복 이름입니다.");
                });

        Users saveUser = userRepository.save(userJoinRequest.toEntity(encoder.encode(userJoinRequest.getPassword())));

        return UserDto.builder()
                .id(saveUser.getId())
                .userName(saveUser.getUserName())
                .email(saveUser.getEmailAddress())
                .grade(saveUser.getGrade())
                .build();

    }


    public String login(UserLoginRequest userLoginRequest) {
        Users users = userRepository.findByUserName(userLoginRequest.getUserName())
                .orElseThrow(() -> new RuntimeException(String.format("%s를 찾을 수 없습니다.", userLoginRequest.getUserName())));

        if (isNotMatch(userLoginRequest, users)) {
            throw new RuntimeException("PASSWORD 틀림");
        }
        return JwtUtils.createToken(userLoginRequest.getUserName(), secretKey, expiredTimeMs);
    }

    public UserDto getUserByUserName(String userName) {
        Users users = userRepository.findByUserName(userName).orElseThrow(() -> new RuntimeException("이름으로 찾을 수 없네요"));
        return UserDto.builder()
                .userName(users.getUserName())
                .grade(users.getGrade())
                .build();
    }

    private boolean isNotMatch(UserLoginRequest userLoginRequest, Users users) {
        return !encoder.matches(userLoginRequest.getPassword(), users.getPassword());
    }

}
