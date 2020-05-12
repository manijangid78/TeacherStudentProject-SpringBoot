package com.mani.CollageBox.Teacher.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherName {

    private String firstName;
    private String middleName;
    private String lastName;

}
