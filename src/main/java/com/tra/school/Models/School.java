package com.tra.school.Models;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String schoolName;
    String location;
    @OneToMany
    @Cascade(CascadeType.ALL)
    List<Student> students;
    Date createdDate;
    Date updatedDate;
    Boolean isActive;


}
