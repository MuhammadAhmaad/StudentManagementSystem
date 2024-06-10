package com.example.springjwt.controllers;

import com.example.springjwt.dto.UserDTO;
import com.example.springjwt.services.UserService;
import com.example.springjwt.utils.StudentManagementSystemUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO) throws Exception{
        String result = userService.createUser(userDTO);
        return ok(StudentManagementSystemUtility.prepareAPIResponse(result));
    }
}
