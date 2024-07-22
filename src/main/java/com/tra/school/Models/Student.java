package com.tra.school.Models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String studentName;
    @OneToMany
    List<Subject> subjects;
    String age;
    String dob;
    Date createdDate;
    Date updatedDate;
    Boolean isActive;

    public Student() {
    }

    public Student(Integer id, String studentName, List<Subject> subjects, String age, String dob, Date createdDate, Date updatedDate, Boolean isActive) {
        this.id = id;
        this.studentName = studentName;
        this.subjects = subjects;
        this.age = age;
        this.dob = dob;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.isActive = isActive;
    }
}
