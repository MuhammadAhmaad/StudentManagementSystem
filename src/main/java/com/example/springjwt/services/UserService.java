package com.example.springjwt.services;

import com.example.springjwt.auth.JwtUtil;
import com.example.springjwt.constants.StudentManagementSystemConstants;
import com.example.springjwt.dto.UserDTO;
import com.example.springjwt.entity.User;
import com.example.springjwt.exception.BusinessException;
import com.example.springjwt.repositories.UserRepository;
import com.example.springjwt.utils.StudentManagementSystemUtility;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String createUser(UserDTO userDTO){
        try{
            User user = StudentManagementSystemUtility.convertToEntity(userDTO, User.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return StudentManagementSystemConstants.SUCCESS_RESPONSE_MESSAGE;
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(StudentManagementSystemConstants.USER_CREATION_ERROR_RESPONSE_CODE,StudentManagementSystemConstants.USER_CREATION_ERROR_RESPONSE_MESSAGE);
        }
    }

    public User getUserFromToken(String token){
        String email = jwtUtil.getEmailFromToken(token);
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if(optionalUser.isPresent())
        {
            return optionalUser.get();
        }else{
            throw new BadCredentialsException("invalid email");
        }
    }
}
