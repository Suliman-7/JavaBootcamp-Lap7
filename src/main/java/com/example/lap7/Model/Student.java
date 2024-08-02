package com.example.lap7.Model;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    @NotNull(message = "student id should be not empty")
    private int id;

    @NotEmpty(message = "student name should be not empty")
    private String name;

    @NotNull(message = "student age should be not empty")
    private int age;

    @NotEmpty(message = "student major should be not empty")
    private String major;

    @AssertFalse(message = "student should be initialize not graduated")
    private boolean isGratuated ;


}