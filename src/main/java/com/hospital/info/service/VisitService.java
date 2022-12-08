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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    public VisitResponse createVisit(VisitCreateRequest dto, String userName) {

//         hospital이 없을 때 등록불가
        Hospital hospital = hospitalRepository.findById(dto.getHospitalId())
                .orElseThrow(() -> new RuntimeException("hospitalId가 없습니다."));

        // user가 없을 때 등록불가
        Users user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("user가 없습니다."));
        Visit visit = Visit.builder()
                .hospital(hospital)
                .user(user)
                .disease(dto.getDisease())
                .amount(dto.getAmount())
                .build();
        Visit savedVisit = visitRepository.save(visit);

        return new VisitResponse(savedVisit.getHospital().getHospitalName(), savedVisit.getUser().getUserName(), savedVisit.getDisease(), savedVisit.getAmount());
    }

    public List<VisitResponse> getList() {
        List<Visit> visitList = visitRepository.findAll();
        List<VisitResponse> visitResponseList = visitList.stream().map(visit -> VisitResponse.builder()
                        .hospitalName(visit.getHospital().getHospitalName())
                        .userName(visit.getUser().getUserName())
                        .disease(visit.getDisease())
                        .amount(visit.getAmount())
                        .build())
                .collect(Collectors.toList());

        return visitResponseList;
    }
}
