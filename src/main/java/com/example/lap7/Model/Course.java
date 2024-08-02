package com.example.lap7.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {

    @NotNull(message = "Id should be not empty")
    private int id;

    @NotEmpty(message = "name should be not empty")
    private String name;

    @NotEmpty(message = "description should be not empty")
    private String description;

    @NotNull(message = "capacity should be not empty")
    @Min(value = 10 , message = "capacity should be at least 10 student ")
    private int capacity;

    @NotEmpty(message = "instructor should be not empty")
    private String instructor;

}
