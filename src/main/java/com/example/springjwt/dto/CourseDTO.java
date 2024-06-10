package com.example.springjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    @NotNull
    private String name;
    @NotNull
    private Date courseStartDate;
    @NotNull
    private Date courseEndDate;
}
