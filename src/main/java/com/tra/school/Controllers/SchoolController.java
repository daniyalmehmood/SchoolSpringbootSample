package com.tra.school.Controllers;

import com.tra.school.DTO.SchoolDTO;
import com.tra.school.Models.School;
import com.tra.school.Services.ReportService;
import com.tra.school.Services.SchoolService;
import com.tra.school.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("school")
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    @Autowired
    SlackService slackService;

    @Autowired
    ReportService reportService;

    @PostMapping("save")
    public School saveSchool(@RequestBody School school) {
        System.out.println("saveSchool is running" + school.toString());
        return schoolService.saveSchool(school);
    }

    @PostMapping("delete")
    public String deleteSchool(@RequestParam String schoolName) {
        System.out.println("deleteSchool is running");
        schoolService.deleteSchool(schoolName);
        return "Success";
    }

    @PostMapping("deleteByLocation")
    public String deleteSchoolByLocation(@RequestParam String loc) {
        schoolService.deleteSchoolByLocation(loc);
        return "Success";
    }

    @GetMapping("getAll")
    public List<SchoolDTO> getSchool() throws Exception {
        reportService.createSchoolReport();
        return schoolService.getSchools();
    }

    @GetMapping("messages")
    public void sendMessage(@RequestParam String channel,
                            @RequestParam String message) {
        slackService.sendMessage(channel, message);
    }

    @GetMapping("word")
    public String sayhi(@RequestParam String word) {
        return word;
    }
}
