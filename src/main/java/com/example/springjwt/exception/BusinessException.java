package com.example.springjwt.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -761503632186596342L;

    String message;
    String code;

    public BusinessException(String code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }
    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

}
