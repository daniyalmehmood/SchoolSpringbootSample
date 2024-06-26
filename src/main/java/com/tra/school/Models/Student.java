package com.tra.school.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
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
}
