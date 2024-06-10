package com.example.springjwt.controllers;

import com.example.springjwt.dto.CourseDTO;
import com.example.springjwt.services.CourseService;
import com.example.springjwt.utils.StudentManagementSystemUtility;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> createUser() throws Exception{
        List<CourseDTO> courses= courseService.getAllCourses();
        return ok(StudentManagementSystemUtility.prepareAPIResponse(courses));
    }

    @GetMapping("/registerToCourse")
    public ResponseEntity<?> registerToCourse(@RequestParam(value = "courseId")String courseId,@RequestHeader (name="Authorization") String token) throws Exception{
        String result = courseService.registerUserToCourse(token,courseId);
        return ok(StudentManagementSystemUtility.prepareAPIResponse(result));
    }

    @GetMapping("/cancelRegistrationToCourse")
    public ResponseEntity<?> cancelRegistrationToCourse(@RequestParam(value = "courseId")String courseId,@RequestHeader (name="Authorization") String token) throws Exception{
        String result = courseService.cancelUserRegistrationToCourse(token,courseId);
        return ok(StudentManagementSystemUtility.prepareAPIResponse(result));
    }

    @GetMapping("/downloadSchedule")
    public ResponseEntity<?> downloadSchedule(@RequestParam(value = "courseId")String courseId) throws IOException {

        ByteArrayOutputStream file = courseService.getCourseSchedule(courseId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Course_Schedule.pdf");
        headers.setContentLength(file.size());
        return new ResponseEntity<>(file.toByteArray(), headers, HttpStatus.OK);
    }
}
