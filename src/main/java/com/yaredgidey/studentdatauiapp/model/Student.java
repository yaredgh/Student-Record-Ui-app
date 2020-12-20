package com.yaredgidey.studentdatauiapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;
@Entity
public class Student {
    @Id
    private Integer id;

    private String name;

    private String lastName;

    private String major;

    private String degreeProgram;

    private String updatedBy;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date updateOn;

    public Student(Integer id, String name, String lastName, String major, String degreeProgram, String updatedBy, Date updateOn) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.major = major;
        this.degreeProgram = degreeProgram;
        this.updatedBy = updatedBy;
        this.updateOn = updateOn;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegreeProgram() {
        return degreeProgram;
    }

    public void setDegreeProgram(String degreeProgram) {
        this.degreeProgram = degreeProgram;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }
}
