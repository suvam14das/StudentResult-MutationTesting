package com.management.student.studentresult.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.management.student.studentresult.utils.JwtUtils;
import com.management.student.studentresult.utils.UserDetailsSecurity;
import com.management.student.studentresult.vo.LoginCredentials;
import com.management.student.studentresult.vo.ResponseMessage;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userDetailsService;

	@Autowired
	private JwtUtils jwtutils;

	public ResponseEntity<ResponseMessage> authenticate(LoginCredentials credentials) throws AuthenticationException{
		
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
		UserDetailsSecurity userDetails = userDetailsService.loadUserByUsername(credentials.getUsername());
		
		Map<String,Object> claims= new HashMap<>();
		claims.put("role", userDetails.getRole());
		final String jwt = jwtutils.generatetoken(claims,userDetails);
		System.out.println(jwt);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(jwt), HttpStatus.OK);

	}
}
