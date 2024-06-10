package com.example.springjwt.exception;

public class ForbiddenException extends RuntimeException  {
    private static final long serialVersionUID = -761503632186596342L;

    String message;
    String code;

    public ForbiddenException(String code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }
    public ForbiddenException(String message) {
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
