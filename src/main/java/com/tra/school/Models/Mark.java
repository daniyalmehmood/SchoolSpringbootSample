package com.tra.school.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String mark;
    String grade;
    Date createdDate;
    Date updatedDate;
    Boolean isActive;
}
