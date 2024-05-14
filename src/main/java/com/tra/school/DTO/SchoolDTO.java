package com.tra.school.DTO;

import com.tra.school.Models.School;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SchoolDTO {
    Integer schoolId;
    String schoolName;
    String schoolLocation;


    public static SchoolDTO convertToDTO(School school) {
        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setSchoolId(school.getId());
        schoolDTO.setSchoolName(school.getSchoolName());
        schoolDTO.setSchoolLocation(school.getLocation());
        return schoolDTO;
    }

    public static List<SchoolDTO> convertToDTO(List<School> schoolList) {
        List<SchoolDTO> schoolDTOS = new ArrayList<>();

        for (School schoolObjectFromDatabase: schoolList) {

            SchoolDTO dto = convertToDTO(schoolObjectFromDatabase);
            schoolDTOS.add(dto);
        }
        return schoolDTOS;
    }
}
