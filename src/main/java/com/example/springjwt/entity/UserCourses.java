package com.example.springjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "USER_COURSES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourses {
    @EmbeddedId
    private UserCoursesID userCoursesID;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "COURSE_ID")
    private Course course;
    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCourses that = (UserCourses) o;
        return Objects.equals(userCoursesID, that.userCoursesID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userCoursesID);
    }
}
