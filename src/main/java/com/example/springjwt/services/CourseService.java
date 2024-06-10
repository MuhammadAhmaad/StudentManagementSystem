package com.example.springjwt.services;

import com.example.springjwt.constants.StudentManagementSystemConstants;
import com.example.springjwt.dto.CourseDTO;
import com.example.springjwt.entity.Course;
import com.example.springjwt.entity.User;
import com.example.springjwt.entity.UserCourses;
import com.example.springjwt.entity.UserCoursesID;
import com.example.springjwt.exception.BusinessException;
import com.example.springjwt.repositories.CourseRepository;
import com.example.springjwt.utils.StudentManagementSystemUtility;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.*;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserService userService;

    public CourseService(CourseRepository courseRepository, UserService userService) {
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    @Cacheable("courses")
    public List<CourseDTO> getAllCourses() {
        List<Course> coursesList = courseRepository.findAll();
        return StudentManagementSystemUtility.convertToDtoList(coursesList, CourseDTO.class);
    }

    public String registerUserToCourse(String token, String stringCourseId) {
        User user = userService.getUserFromToken(token);
        Course course = getCourseById(stringCourseId);
        if (checkIfAlreadyReserved(user, course))
            throw new BusinessException(StudentManagementSystemConstants.USER_COURSE_ALREADY_REGISTERED_ERROR_RESPONSE_CODE, StudentManagementSystemConstants.USER_COURSE_ALREADY_REGISTERED_ERROR_RESPONSE_MESSAGE);
        UserCourses userCourses = new UserCourses();
        UserCoursesID userCoursesID = new UserCoursesID(user.getId(), course.getId());
        userCourses.setUserCoursesID(userCoursesID);
        userCourses.setCourse(course);
        userCourses.setRegistrationDate(new Date());
        course.getUserCoursesList().add(userCourses);
        courseRepository.save(course);
        return StudentManagementSystemConstants.SUCCESS_RESPONSE_MESSAGE;
    }

    public String cancelUserRegistrationToCourse(String token, String stringCourseId) {
        User user = userService.getUserFromToken(token);
        Course course = getCourseById(stringCourseId);
        if (!checkIfAlreadyReserved(user, course))
            throw new BusinessException(StudentManagementSystemConstants.USER_COURSE_ALREADY_NOT_REGISTERED_ERROR_RESPONSE_CODE, StudentManagementSystemConstants.USER_COURSE_ALREADY_NOT_REGISTERED_ERROR_RESPONSE_MESSAGE);
        UserCourses userCourses = new UserCourses();
        UserCoursesID userCoursesID = new UserCoursesID(user.getId(), course.getId());
        userCourses.setUserCoursesID(userCoursesID);
        userCourses.setCourse(course);
        course.getUserCoursesList().remove(userCourses);
        courseRepository.save(course);
        return StudentManagementSystemConstants.SUCCESS_RESPONSE_MESSAGE;
    }

    private boolean checkIfAlreadyReserved(User user, Course course) {
        List<UserCourses> userCourses = course.getUserCoursesList();
        if (userCourses != null && !userCourses.isEmpty()) {
            for (UserCourses userCourse : userCourses) {
                if (Objects.equals(userCourse.getUserCoursesID().getUserId(), user.getId()))
                    return true;
            }
        }
        return false;
    }

    public ByteArrayOutputStream getCourseSchedule(String stringCourseId) {
        Course course = getCourseById(stringCourseId);
        try {
            int noOfColumns = 2;
            List<String> headers = new ArrayList<>();
            headers.add("Key");
            headers.add("value");
            List<String> cells = new ArrayList<>();
            cells.add("Course Name ");
            cells.add(course.getName());
            cells.add("Course Start Date ");
            cells.add(course.getCourseStartDate().toString());
            cells.add("Course End Date");
            cells.add(course.getCourseEndDate().toString());
            return StudentManagementSystemUtility.createPDFTable(noOfColumns, headers, cells);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(StudentManagementSystemConstants.CREATING_PDF_ERROR_RESPONSE_CODE, StudentManagementSystemConstants.CREATING_PDF_ERROR_RESPONSE_MESSAGE);
        }
    }

    private Course getCourseById(String stringCourseId) {
        Long courseId = Long.parseLong(stringCourseId);
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            return optionalCourse.get();
        } else {
            throw new BusinessException(StudentManagementSystemConstants.COURSE_NOT_FOUND_ERROR_RESPONSE_CODE, StudentManagementSystemConstants.COURSE_NOT_FOUND_ERROR_RESPONSE_MESSAGE);
        }
    }
}
