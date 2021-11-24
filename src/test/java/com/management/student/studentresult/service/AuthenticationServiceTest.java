package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Auth;
import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.utils.JwtUtils;
import com.management.student.studentresult.utils.UserDetailsSecurity;
import com.management.student.studentresult.vo.LoginCredentials;
import com.management.student.studentresult.vo.ResponseMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticateService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserService userDetailsService;

    @Mock
    private JwtUtils jwtutils;

    @Test
    public void authenticateTestWrongUser(){
        LoginCredentials credentials = new LoginCredentials("MT0000000", "password");
        String exceptionMessage = "The username"+credentials.getUsername()+"does not exist!";
        Mockito.when(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())))
                .thenThrow(new UsernameNotFoundException(exceptionMessage));
        AuthenticationException exception = Assertions.assertThrows(AuthenticationException.class,() -> authenticateService.authenticate(credentials)) ;
        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    public void authenticateTestValidUser(){
        String jwtToken =  "JwtToken:)";
        LoginCredentials credentials = new LoginCredentials("MT2020093", "password");
        UserDetailsSecurity userDetailsSecurity = new UserDetailsSecurity(new User(new Auth("MT2020093", "password"), new Role("student", null, null), "MT2020093", "Aditya Saha", "Address", null, null));
        Mockito.when(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()))).thenReturn(null);
        Mockito.when(userDetailsService.loadUserByUsername(credentials.getUsername())).thenReturn(userDetailsSecurity);
        Mockito.when(jwtutils.generatetoken(any(Map.class) ,any(UserDetailsSecurity.class))).thenReturn(jwtToken);
        ResponseEntity<ResponseMessage> response =  authenticateService.authenticate(credentials);
        Assertions.assertEquals(jwtToken, response.getBody().getMessage());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
