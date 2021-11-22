package com.management.student.studentresult.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.management.student.studentresult.service.AuthenticationService;
import com.management.student.studentresult.service.RoleService;
import com.management.student.studentresult.service.UserService;
import com.management.student.studentresult.vo.LoginCredentials;
import com.management.student.studentresult.vo.ResponseMessage;
import com.management.student.studentresult.vo.UserDetails;

import java.text.ParseException;


@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;    
    
    
    @Autowired
    private AuthenticationService authService;

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<ResponseMessage> registerUser(@RequestBody UserDetails userDetails){
    	return userService.registrationService(userDetails);
    }

    @PostMapping("/authenticate")
    public @ResponseBody ResponseEntity<ResponseMessage> authenticateUser(@RequestBody LoginCredentials credentials) {
    	return authService.authenticate(credentials);
    }

    @GetMapping("/user/details")
    public @ResponseBody ResponseEntity<?> getUserDetails(@RequestParam String extId){
        UserDetails userDetail;
        try {
            userDetail = userService.getUserDetailsByExtId(extId);
            return new ResponseEntity<>(userDetail, HttpStatus.OK);
        } catch (ParseException e) {
            return new ResponseEntity<String>("Failed to get the user", HttpStatus.NOT_FOUND);
        }
    }

}
