package com.tra.school.repositories;

import com.tra.school.Models.School;
import com.tra.school.Repositories.SchoolRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class SchoolRepositoryTest {

    @Autowired
    private SchoolRepository schoolRepository;

    @BeforeEach
    void setUp() {
        School school1 = School.builder().schoolName("Springfield Elementary")
                .location("Springfield").build();

        School school2 = School.builder().schoolName("ABQ Elementary")
                .location("Sohar").build();

        schoolRepository.save(school1);
        schoolRepository.save(school2);
    }

    @Test
    public void testGetBySchoolName() {
        School school = schoolRepository.getBySchoolName("Springfield Elementary");
        Assertions.assertThat(school).isNotNull();
        Assertions.assertThat(school.getSchoolName()).isEqualTo("Springfield Elementary");
    }

    @Test
    public void testGetSchoolByLocation() {
        List<School> schools = schoolRepository.getSchoolByLocation("Sohar");
        Assertions.assertThat(schools).hasSize(1);
        Assertions.assertThat(schools.get(0).getSchoolName()).isEqualTo("ABQ Elementary");
    }
}