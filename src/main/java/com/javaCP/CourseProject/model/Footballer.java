package com.javaCP.CourseProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_footballers")
public class Footballer {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "footballer_id")
private Long id;
        
@Column(name = "footballer_firstname")
private String firstname;
        
@Column(name = "footballer_lastname")
private String lastname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }      
}