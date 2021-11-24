package com.management.student.studentresult.securityUtils;

import com.management.student.studentresult.vo.ResponseMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void AuthenticationExceptionHandlerTest(){
        String message = "Bad Credentials!";
        AuthenticationException ex = new UsernameNotFoundException("");
        ResponseEntity<ResponseMessage> response = this.globalExceptionHandler.AuthenticationExceptionHandler(ex);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(message, response.getBody().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void UsernameNotFoundExceptionHandlerTest(){
        String message = "Username not found";
        UsernameNotFoundException ex = new UsernameNotFoundException(message);
        ResponseEntity<ResponseMessage> response = this.globalExceptionHandler.UsernameNotFoundExceptionHandler(ex);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(message, response.getBody().getMessage());
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void ExpiredJwtExceptionHandlerTest(){
        String message = "Session expired!";
        Exception e = new Exception("");
        ResponseEntity<ResponseMessage> response = this.globalExceptionHandler.ExpiredJwtExceptionHandler(e);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(message, response.getBody().getMessage());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void globalExceptionHandlerTest(){
        String message = "We are unable to process this request!";
        Exception e = new Exception("");
        ResponseEntity<ResponseMessage> response = this.globalExceptionHandler.globalExceptionHandler(e);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(message, response.getBody().getMessage());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
