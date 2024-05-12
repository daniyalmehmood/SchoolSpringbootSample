package com.tra.school.Controllers;

import com.tra.school.Models.School;
import com.tra.school.Models.Student;
import com.tra.school.Repositories.SchoolRepository;
import com.tra.school.Services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("school")
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    @PostMapping("save")
    public School saveSchool(@RequestBody School school) {
        return schoolService.saveSchool(school);
    }

    @PostMapping("delete")
    public String deleteSchool(@RequestParam String schoolName) {
        schoolService.deleteSchool(schoolName);
        return "Success";
    }

    @PostMapping("deleteByLocation")
    public String deleteSchoolByLocation(@RequestParam String loc) {
        schoolService.deleteSchoolByLocation(loc);
        return "Success";
    }

/*    @PutMapping("update")
    public School update(@RequestParam Integer schoolId,
                         @RequestParam String schoolName) {
        School school = schoolRepository.getById(schoolId);
        if (!school.equals(null)) {
            school.setSchoolName(schoolName);
            school = schoolRepository.save(school);
        }
        return school;
    }*/
}
