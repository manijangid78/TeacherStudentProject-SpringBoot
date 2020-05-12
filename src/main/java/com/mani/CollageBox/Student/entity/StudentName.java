package com.mani.CollageBox.Student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentName {

    private String firstName;
    private String middleName;
    private String lastName;

}
