package com.hospital.info.domain.entity;

import com.hospital.info.domain.dto.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospital {
    @Id
    @Column(name = "hospital_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "hospital_name")
    private String hospitalName;


//    public static HospitalResponse of(Hospital hospital) {
//        return new HospitalResponse(hospital.getId(), hospital.getRoadNameAddress(), hospital.getHospitalName(), hospital.getPatientRoomCount(),
//                hospital.getTotalNumberOfBeds(), hospital.getBusinessTypeName(), hospital.getTotalAreaSize());
//    }
}
