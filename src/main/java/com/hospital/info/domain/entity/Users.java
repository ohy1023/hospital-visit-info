package com.hospital.info.domain.entity;

import com.hospital.info.domain.enums.UserGrade;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@DynamicInsert
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "unique_user_name", columnNames = {"userName"})
})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String userName;
    private String password;

    private String emailAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade", length = 30, columnDefinition = "varchar(30) default 'REGULAR'")
    private UserGrade grade;

    @PrePersist
    public void prePersist() {
        this.grade = this.grade == null ? UserGrade.REGULAR : this.grade;
    }

    @Builder
    public Users(Long id, String userName, String password, String emailAddress, UserGrade grade) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.grade = grade;
    }
}
