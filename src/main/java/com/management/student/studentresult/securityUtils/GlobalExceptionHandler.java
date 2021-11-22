package com.management.student.studentresult.securityUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.management.student.studentresult.vo.ResponseMessage;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ResponseMessage> AuthenticationExceptionHandler(AuthenticationException ex) {
		return new ResponseEntity<ResponseMessage>(new ResponseMessage("Bad Credentials!"), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ResponseMessage> UsernameNotFoundExceptionHandler(UsernameNotFoundException ex) {
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(ex.getMessage()), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<ResponseMessage> ExpiredJwtExceptionHandler(Exception ex) {
		return new ResponseEntity<ResponseMessage>(new ResponseMessage("Session expired!"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> globalExceptionHandler(Exception ex) {
		return new ResponseEntity<ResponseMessage>(new ResponseMessage("We are unable to process this request!"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
