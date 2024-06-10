package com.example.springjwt.constants;

public class StudentManagementSystemConstants {
    public static final String SUCCESS_RESPONSE_CODE = "000";
    public static final String SUCCESS_RESPONSE_MESSAGE = "The transaction has been ended successfully ";

    public static final String GENERAL_ERROR_RESPONSE_CODE = "001";
    public static final String GENERAL_ERROR_RESPONSE_MESSAGE = "The transaction has been failed for reason General Error";

    public static final String BUSINESS_ERROR_RESPONSE_CODE = "002";
    public static final String BUSINESS_ERROR_RESPONSE_MESSAGE = "The transaction has been failed for reason BUSINESS Error";

    public static final String BAD_ERROR_RESPONSE_CODE = "003";
    public static final String BAD_ERROR_RESPONSE_MESSAGE = "The transaction has been failed for reason BAD Request Error";
    public static final String FORBIDDEN_ERROR_RESPONSE_CODE = "403";
    public static final String FORBIDDEN_ERROR_RESPONSE_MESSAGE = "user is not allowed";

    public static final String USER_CREATION_ERROR_RESPONSE_CODE = "004";
    public static final String USER_CREATION_ERROR_RESPONSE_MESSAGE = "error while creating user";

    public static final String COURSE_NOT_FOUND_ERROR_RESPONSE_CODE = "005";
    public static final String COURSE_NOT_FOUND_ERROR_RESPONSE_MESSAGE = "invalid course id, course not found";

    public static final String USER_COURSE_ALREADY_REGISTERED_ERROR_RESPONSE_CODE = "006";
    public static final String USER_COURSE_ALREADY_REGISTERED_ERROR_RESPONSE_MESSAGE = "user already registered to this course";

    public static final String USER_COURSE_ALREADY_NOT_REGISTERED_ERROR_RESPONSE_CODE = "007";
    public static final String USER_COURSE_ALREADY_NOT_REGISTERED_ERROR_RESPONSE_MESSAGE = "user already not registered to this course";
    public static final String CREATING_PDF_ERROR_RESPONSE_CODE = "008";
    public static final String CREATING_PDF_ERROR_RESPONSE_MESSAGE = "error while creating pdf";

}
