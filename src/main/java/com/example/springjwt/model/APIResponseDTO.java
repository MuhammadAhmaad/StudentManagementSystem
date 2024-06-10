package com.example.springjwt.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponseDTO extends BaseResponseDTO implements Serializable {


    private static final long serialVersionUID = -6986746375915710855L;

    private String responseMessage;
    private Object data;

}
