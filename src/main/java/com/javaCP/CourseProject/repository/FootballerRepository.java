package com.javaCP.CourseProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaCP.CourseProject.model.Footballer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FootballerRepository extends JpaRepository<Footballer, Long> {
    
    @Query(value="select * from tbl_footballers e where e.footballer_firstname like %:keyword% or e.footballer_lastname like %:keyword%", nativeQuery=true)
    List<Footballer> findByKeyword(@Param("keyword") String keyword);
}
