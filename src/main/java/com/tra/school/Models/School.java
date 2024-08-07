package com.tra.school.Models;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
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

    public School(Integer id, String schoolName, String location, List<Student> students, Date createdDate, Date updatedDate, Boolean isActive) {
        this.id = id;
        this.schoolName = schoolName;
        this.location = location;
        this.students = students;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.isActive = isActive;
    }

    public School() {
    }
}
