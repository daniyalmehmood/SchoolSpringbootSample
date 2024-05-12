package com.tra.school.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String subjectName;
    Date createdDate;
    Date updatedDate;
    Boolean isActive;
    @OneToMany
    List<Mark> marks;
}
