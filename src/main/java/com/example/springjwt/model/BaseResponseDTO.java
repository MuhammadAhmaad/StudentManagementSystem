package com.example.springjwt.model;

import java.io.Serializable;

public class BaseResponseDTO implements Serializable {

    private static final long serialVersionUID = -6986746375915710855L;

    private String responseCode;
    private String responseDate;
    public BaseResponseDTO(){

    }
    public BaseResponseDTO(String responseCode, String responseDate) {
        this.responseCode = responseCode;
        this.responseDate = responseDate;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }
}