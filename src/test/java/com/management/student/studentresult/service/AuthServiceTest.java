package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Auth;
import com.management.student.studentresult.repository.AuthRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class AuthServiceTest {
    @InjectMocks
    private AuthService authService;

    @Mock
    private AuthRepository repository;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    private Auth getAuth(){
        return new Auth("sample@gmail.com", "password");
    }

    @Test
    public void saveAuthTest(){
        Mockito.when(repository.save(any(Auth.class))).thenReturn(getAuth());
        Auth auth = this.authService.saveAuth(getAuth());
        Assertions.assertNotNull(auth);
        Assertions.assertEquals("sample@gmail.com",auth.getEmail());
        Assertions.assertEquals("password",auth.getPassword());
    }

    @Test
    public void getAuthByEmailTest(){
        Mockito.when(repository.findByEmail(anyString())).thenReturn(Optional.of(getAuth()));
        Optional<Auth> authOptional = this.authService.getAuthByEmail(getAuth().getEmail());
        Assertions.assertTrue(authOptional.isPresent());
        Auth auth = authOptional.get();
        Assertions.assertEquals(getAuth().getEmail(), auth.getEmail());
        Assertions.assertEquals(getAuth().getPassword(), auth.getPassword());
    }
}
