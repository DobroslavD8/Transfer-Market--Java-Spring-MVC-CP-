package com.javaCP.CourseProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaCP.CourseProject.model.Footballer;
import com.javaCP.CourseProject.repository.FootballerRepository;

@Service
public class FootballerService {
    
    @Autowired
    private FootballerRepository repository;
    
    public List<Footballer> listAll() {
        return repository.findAll();
    }
    
    public void create(Footballer footballer) {
        repository.save(footballer);
    }
    
    public Footballer updateid(Long id) {
        return repository.findById(id).get();
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
