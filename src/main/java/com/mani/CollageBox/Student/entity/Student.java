package com.mani.CollageBox.Student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue
    private int id;
    private String rollNo;
    private StudentName studentName;
    private String branch;
    private String MobileNo;
    private String password;
    private int semester;

}
