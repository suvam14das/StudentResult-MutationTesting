package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Auth;
import com.management.student.studentresult.repository.AuthRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository repository;

    public Auth saveAuth(Auth auth){
        return repository.save(auth);
    }

	public Optional<Auth> getAuthByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email);
	}
}
