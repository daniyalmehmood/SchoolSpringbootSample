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

    /*
    This line tells Mockito to create a mock instance of the SchoolRepository class.
    This mock can then be used to simulate interactions with the repository
    in a controlled way during your tests.
    */
    @Mock
    private SchoolRepository schoolRepository;

    /*
    This tells Mockito to create an instance of the SchoolService class
    and automatically inject the mock instance of SchoolRepository into it.
    This allows you to test SchoolService in isolation by using the mock SchoolRepository.
     */
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

        /*
        when: This is a Mockito method used to specify a behavior for a
                mock object when a particular method is called.

        schoolRepository.save(any(School.class)): This specifies that when the save method of
                                                  schoolRepository is called with any School object,
                                                  the behavior should be defined.

        thenReturn(school): This specifies that the save method should return the school object
                            when it is called with any School object.
        */

        /*
        This line effectively tells Mockito that when the save method is called on
        schoolRepository with any School object, it should return the predefined school object.
        */
        when(schoolRepository.save(any(School.class))).thenReturn(school);


        School savedSchool = schoolService.saveSchool(school);



        /*
        verify: This is a Mockito method used to check if a particular method on a mock object was called.
        schoolRepository: This is the mock object on which the method call is verified.
        times(1): This specifies that the save method should be called exactly once.
        save(any(School.class)): This specifies that the save method should be called with any School object.
        */

        /*
        This line checks that the save method of
        schoolRepository was called exactly once with any School object.
        */
        verify(schoolRepository, times(1)).save(any(School.class));


        // Assert the returned school matches the mock data
        assertEquals("Springfield Elementary", savedSchool.getSchoolName());
    }

    @Test
    void testDeleteSchool() {
        String schoolName = "Springfield Elementary";

        // Mock repository method to return a mock School
        School schoolByLocationName = School.builder()
                .schoolName(schoolName)
                .isActive(true)
                .build();

        School schoolToDelete = School.builder()
                .schoolName(schoolName)
                .isActive(false)
                .build();

        when(schoolRepository.getBySchoolName(schoolName)).thenReturn(schoolByLocationName);
        when(schoolRepository.save(any(School.class))).thenReturn(schoolToDelete);


        // Call service method
        School schoolDeleteResult = schoolService.deleteSchool(schoolName);

        // Verify repository method was called once
        verify(schoolRepository, times(1)).getBySchoolName(schoolName);
        verify(schoolRepository, times(1)).save(schoolToDelete);

        assertNotNull(schoolDeleteResult);
        // Assert that isActive is set to false
        assertEquals(false, schoolDeleteResult.getIsActive());

        assertEquals("Springfield Elementary", schoolDeleteResult.getSchoolName());
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