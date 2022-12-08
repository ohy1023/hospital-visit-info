package com.hospital.info.auditing;

import com.hospital.info.domain.entity.Visit;
import com.hospital.info.repository.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class AuditingTest {
    @Autowired
    VisitRepository visitRepository;

    @Test
    void auditingTest() {

        Visit visit = Visit.builder()
                .amount(3000).build();
        Visit save = visitRepository.save(visit);

        System.out.println("save.getAmount() = " + save.getAmount());
        System.out.println("save.getCreatedAt() = " + save.getCreatedAt());

    }
}
