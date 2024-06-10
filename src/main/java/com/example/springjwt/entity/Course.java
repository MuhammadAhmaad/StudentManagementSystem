package com.example.springjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "COURSE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "COURSE_START_DATE")
    private Date courseStartDate;
    @Column(name = "COURSE_END_DATE")
    private Date courseEndDate;
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserCourses> userCoursesList;
}
