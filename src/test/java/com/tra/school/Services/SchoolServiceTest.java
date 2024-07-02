package com.tra.school.Services;

import com.tra.school.DTO.SchoolDTO;
import com.tra.school.Models.School;
import com.tra.school.Repositories.SchoolRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SchoolServiceTest {

    @Mock
    private SchoolRepository schoolRepository;

    @InjectMocks
    private SchoolService schoolService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSchool() {
        String location = "Springfield";
        String schoolName = "Springfield Elementary";
        // Prepare mock data
        School school =  School.builder()
                .schoolName(schoolName)
                .isActive(true)
                .location(location)
                .build();
        school.setSchoolName("Springfield Elementary");
        school.setLocation("Springfield");

        // Mock repository method
        when(schoolRepository.save(any(School.class))).thenReturn(school);

        // Call service method
        School savedSchool = schoolService.saveSchool(school);

        // Verify repository method was called once
        verify(schoolRepository, times(1)).save(any(School.class));

        // Assert the returned school matches the mock data
        assertEquals("Springfield Elementary", savedSchool.getSchoolName());
    }

    @Test
    void testDeleteSchool() {
        String schoolName = "Springfield Elementary";

        // Mock repository method to return a mock School
        School mockSchool = School.builder()
                .schoolName(schoolName)
                .isActive(true)
                .build();
        when(schoolRepository.getBySchoolName(schoolName)).thenReturn(mockSchool);

        // Call service method
        String result = schoolService.deleteSchool(schoolName);

        // Verify repository method was called once
        verify(schoolRepository, times(1)).getBySchoolName(schoolName);
        verify(schoolRepository, times(1)).save(mockSchool);

        // Assert the result is "Success"
        assertEquals("Success", result);
        // Assert that isActive is set to false
        assertEquals(false, mockSchool.getIsActive());
    }

    @Test
    void testDeleteSchoolByLocation() {
        String location = "Springfield";
        String schoolName = "Springfield Elementary";


        // Mock repository method to return a list of mock Schools
        List<School> mockSchools = new ArrayList<>();
        School mockSchool1 = School.builder()
                .schoolName(schoolName)
                .isActive(true)
                .location(location)
                .build();
        mockSchools.add(mockSchool1);

        when(schoolRepository.getSchoolByLocation(location)).thenReturn(mockSchools);

        // Call service method
        String result = schoolService.deleteSchoolByLocation(location);

        // Verify repository method was called once for saveAll
        verify(schoolRepository, times(1)).saveAll(mockSchools);

        // Assert the result is "Success"
        assertEquals("Success", result);

        // Assert that all schools in the list have isActive set to false
        for (School school : mockSchools) {
            assertEquals(false, school.getIsActive());
        }
    }

    @Test
    void testGetSchools() {
        String location = "Springfield";
        String schoolName = "Springfield Elementary";

        // Mock repository method to return a list of mock Schools
        List<School> mockSchools = new ArrayList<>();
        School mockSchool1 = School.builder()
                .schoolName(schoolName)
                .isActive(true)
                .location(location)
                .build();
        mockSchools.add(mockSchool1);

        when(schoolRepository.findAll()).thenReturn(mockSchools);

        // Call service method
        List<SchoolDTO> schoolDTOs = schoolService.getSchools();

        // Verify repository method was called once
        verify(schoolRepository, times(1)).findAll();

        // Assert that the returned list is converted to DTO correctly
        assertEquals(1, schoolDTOs.size());
        assertEquals("Springfield Elementary", schoolDTOs.get(0).getSchoolName());
    }

}