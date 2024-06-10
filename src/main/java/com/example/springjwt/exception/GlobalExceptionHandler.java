package com.example.springjwt.exception;

import com.example.springjwt.constants.StudentManagementSystemConstants;
import com.example.springjwt.model.APIResponseDTO;
import com.example.springjwt.model.ErrorDetailsResponse;
import com.example.springjwt.utils.DateTimeUtils;
import com.example.springjwt.utils.StudentManagementSystemUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> BusinessExceptionHandler(BusinessException ex, WebRequest request)
            throws JsonProcessingException {

        logger.error(ex.getMessage(), ex);

        APIResponseDTO response = StudentManagementSystemUtility.prepareAPIResponse(ex.message,
                null, ex.code);

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> BadRequestExceptionHandler(BadRequestException ex, WebRequest request)
            throws JsonProcessingException {

        logger.error(ex.getMessage(), ex);
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(ex.getCode(),
                DateTimeUtils.createDateStringFromDate(null),
                StudentManagementSystemConstants.BAD_ERROR_RESPONSE_MESSAGE + " / " + ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> ForbiddenExceptionHandler(ForbiddenException ex, WebRequest request)
            throws JsonProcessingException {

        logger.error(ex.getMessage(), ex);
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(ex.getCode(),
                DateTimeUtils.createDateStringFromDate(null),
                StudentManagementSystemConstants.FORBIDDEN_ERROR_RESPONSE_MESSAGE + " / " + ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) throws JsonProcessingException {

        logger.error(ex.getMessage(), ex);
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(StudentManagementSystemConstants.GENERAL_ERROR_RESPONSE_CODE,
                DateTimeUtils.createDateStringFromDate(null),
                StudentManagementSystemConstants.GENERAL_ERROR_RESPONSE_CODE + " /  " + ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
