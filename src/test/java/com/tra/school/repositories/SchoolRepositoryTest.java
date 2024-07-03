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


/*
* @DataJpaTest is a specialized annotation in Spring Boot for testing JPA repositories.
* It focuses on testing the persistence layer components (like repositories) and
* configures in-memory databases (such as H2) by default.
* This allows you to test your JPA entities, repositories, and related configurations
* without requiring a full application context.
 */
@DataJpaTest

/*
* @ActiveProfiles("test") annotation in Spring is used to specify which
* profiles should be active for a particular test.
* Profiles are a way of grouping configuration settings,
* and they allow you to change configurations
* depending on the environment (e.g., development, testing, production).
* */
@ActiveProfiles("test")

/*
* @AutoConfigureTestDatabase annotation in Spring Boot is used to configure
* the test database
* that should be used for tests.
* When you use @DataJpaTest or other similar testing annotations,
* Spring Boot automatically configures an in-memory database
* (like H2, HSQL, or Derby) by default.
* The @AutoConfigureTestDatabase annotation allows you to customize this behavior.
* */
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class SchoolRepositoryTest {

    @Autowired
    private SchoolRepository schoolRepository;

    /*
    * @BeforeEach annotation in JUnit 5 is used to specify that a particular method should be
    * executed before each test method in the test class.
    * */
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