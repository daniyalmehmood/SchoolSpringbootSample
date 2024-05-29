package com.tra.school.Services;

import com.tra.school.Models.Student;
import com.tra.school.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    public Student saveStudent(Student student){
        //Student student = new Student();
        student.setStudentName("Abdullah");
        student.setCreatedDate(new Date());
        student.setIsActive(Boolean.TRUE);

        return studentRepository.save(student);
    }

    public Student getStudentByName(String studentName){
        return studentRepository.getStudentByName(studentName);
    }
}
