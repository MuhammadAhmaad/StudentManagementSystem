package com.example.springjwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ErrorDetailsResponse extends BaseResponseDTO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String error;

    public ErrorDetailsResponse(String responseCode, String responseDate, String error) {
        super(responseCode, responseDate);
        this.error = error;
    }

}