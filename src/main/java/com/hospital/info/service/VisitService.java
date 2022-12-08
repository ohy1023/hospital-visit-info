package com.hospital.info.service;

import com.hospital.info.domain.dto.VisitCreateRequest;
import com.hospital.info.domain.dto.VisitResponse;
import com.hospital.info.domain.entity.Hospital;
import com.hospital.info.domain.entity.Users;
import com.hospital.info.domain.entity.Visit;
import com.hospital.info.repository.HospitalRepository;
import com.hospital.info.repository.UserRepository;
import com.hospital.info.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    public VisitResponse createVisit(VisitCreateRequest dto, String userName) {

//         hospital이 없을 때 등록불가
        Hospital hospital = hospitalRepository.findById(dto.getHospitalId())
                .orElseThrow(() -> new RuntimeException(String.format("hospitalId:%s 가 없습니다.", dto.getHospitalId())));

        // user가 없을 때 등록불가
        Users user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException(String.format("%s user가 없습니다.", userName)));
        Visit visit = Visit.builder()
                .hospital(hospital)
                .user(user)
                .disease(dto.getDisease())
                .amount(dto.getAmount())
                .build();
        Visit savedVisit = visitRepository.save(visit);

        return new VisitResponse(savedVisit.getHospital().getHospitalName(), savedVisit.getUser().getUserName(), savedVisit.getDisease(), savedVisit.getAmount());
    }
}
