package com.tra.school.Controllers;

import com.tra.school.DTO.SchoolDTO;
import com.tra.school.Models.School;
import com.tra.school.Services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("getAll")
    public List<SchoolDTO> getSchool(){
        return schoolService.getSchools();
    }
}
