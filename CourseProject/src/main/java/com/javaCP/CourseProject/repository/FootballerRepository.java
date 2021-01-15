package com.javaCP.CourseProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaCP.CourseProject.model.Footballer;
import org.springframework.beans.factory.annotation.Autowired;

public interface FootballerRepository extends JpaRepository<Footballer, Long> {
    
}
