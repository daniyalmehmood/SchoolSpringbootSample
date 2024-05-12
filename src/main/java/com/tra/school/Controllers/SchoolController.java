package com.tra.school.Controllers;

import com.tra.school.Models.School;
import com.tra.school.Models.Student;
import com.tra.school.Repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("school")
public class SchoolController {

    @Autowired
    SchoolRepository schoolRepository;

    @PostMapping("save")
    public School saveSchool() {
        School school = new School();

        school.setSchoolName("School of Abdullah Bin Rawaha");
        school.setLocation("Samail");
        school.setCreatedDate(new Date());
        school.setIsActive(Boolean.TRUE);

        Student student = new Student();
        student.setStudentName("Abdullah");
        student.setCreatedDate(new Date());
        student.setIsActive(Boolean.TRUE);

        school.setStudents(Arrays.asList(student));

        return schoolRepository.save(school);
    }

    @PutMapping("update")
    public School update(@RequestParam Integer schoolId,
                         @RequestParam String schoolName) {
        School school = schoolRepository.getById(schoolId);
        if (!school.equals(null)) {
            school.setSchoolName(schoolName);
            school = schoolRepository.save(school);
        }
        return school;
    }
}
