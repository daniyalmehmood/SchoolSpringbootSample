package com.tra.school.Services;

import com.tra.school.Models.School;
import com.tra.school.Models.Student;
import com.tra.school.Repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class SchoolService {


    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    StudentService studentService;

    public School saveSchool(School school){

        school.setCreatedDate(new Date());
        school.setIsActive(Boolean.TRUE);

        return schoolRepository.save(school);
    }


    public String deleteSchool(String schoolName){
        School schoolFromDb = schoolRepository.getBySchoolName(schoolName);
        schoolFromDb.setIsActive(Boolean.FALSE);
        schoolRepository.save(schoolFromDb);
        return "Success";
    }

    public String deleteSchoolByLocation(String location) {
        List<School> schools = schoolRepository.getSchoolByLocation(location);

        List<School> updatedSchoolList = new ArrayList<>();

        for (School school: schools) {
            school.setIsActive(false);
            updatedSchoolList.add(school);
        }
        schoolRepository.saveAll(updatedSchoolList);

        return "Success";
    }


}
