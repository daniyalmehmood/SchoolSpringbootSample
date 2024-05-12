package com.tra.school.Repositories;

import com.tra.school.Models.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchoolRepository extends JpaRepository <School, Integer>{

    @Query("SELECT s from School s WHERE s.schoolName =:schoolName")
    School getBySchoolName(@Param("schoolName") String schoolName);

    @Query("SELECT sc from School sc WHERE sc.location =:loc")
    List<School> getSchoolByLocation(@Param("loc") String location);
}
