package com.hospital.info.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

//    @ManyToOne
//    @JoinColumn(name = "code")
    private String disease;

    private int amount;

    @Builder
    public Visit(Long id, Hospital hospital, Users user, String disease, int amount) {
        this.id = id;
        this.hospital = hospital;
        this.user = user;
        this.disease = disease;
        this.amount = amount;
    }
}
