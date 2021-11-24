package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Auth;
import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.repository.UserRepository;
import com.management.student.studentresult.utils.UserDetailsSecurity;
import com.management.student.studentresult.vo.ResponseMessage;
import com.management.student.studentresult.vo.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;

public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private AuthService authService;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    public User getUser(){
        return new User(
                new Auth("sample@gmail.com", "password"),
                new Role("student", null, null),
                "MT20200000", "Aditya Saha", "Address", "9009900889", null);
    }

    public UserDetails getUserDetails(){
        return new UserDetails("Aditya Saha", "MT2020093", null, "address", "9009009009",
                "sample@gmail.com", "password", -1);
    }
    @Test
    public void saveUserTest(){

        User user = getUser();
        Mockito.when(repository.save(user)).thenReturn(user);
        User outputUser = this.userService.saveUser(user);
        Assertions.assertEquals(user.getExtId(), outputUser.getExtId());
    }

    @Test
    public void getUsersTest(){
        List<User> userList = new ArrayList<>();
        userList.add(getUser());userList.add(getUser());
        Mockito.when(repository.findAll()).thenReturn(userList);
        List<User> listUser = this.userService.getUsers();
        Assertions.assertNotNull(listUser);
        Assertions.assertEquals(2, listUser.size());
    }

    @Test
    public void getUserByIdTest(){
        User user = getUser();
        user.setUserId(1);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(user));
        User userById = this.userService.getUserById(1);
        Assertions.assertEquals(user.getUserId(), userById.getUserId());
    }

    @Test
    public void getUserByExtIdTest(){
        User user = getUser();
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(user));
        User userByExtId = this.userService.getUserById(1);
        Assertions.assertEquals(user.getExtId(), userByExtId.getExtId());
    }

    @Test
    public void getUserDetailsByExtIdTest() throws Exception{
        User user = getUser();
        Mockito.when(this.userService.getUserByExtId(anyString())).thenReturn(user);
        UserDetails userDetails = this.userService.getUserDetailsByExtId(user.getExtId());
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(user.getExtId(), userDetails.getExtId());
        Assertions.assertEquals(user.getName(), userDetails.getName());
        Assertions.assertEquals(user.getAddress(), userDetails.getAddress());
        Assertions.assertEquals(user.getAuth().getEmail(), userDetails.getEmail());
        Assertions.assertEquals(user.getPhone(), userDetails.getContactno());
    }

    @Test
    public void registrationServiceExistingEmailTest(){
        UserDetails userDetails = getUserDetails();
        Mockito.when(authService.getAuthByEmail(userDetails.getEmail())).thenReturn(Optional.of(getUser().getAuth()));
        ResponseEntity<ResponseMessage> response = this.userService.registrationService(userDetails);
        Assertions.assertEquals("Email already exists!", response.getBody().getMessage());
        Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());

    }


    @Test
    public void registrationServiceExistingContactTest() {
        UserDetails userDetails = getUserDetails();
        Mockito.when(repository.existsByPhone(userDetails.getContactno())).thenReturn(Boolean.TRUE);
        ResponseEntity<ResponseMessage> response = this.userService.registrationService(userDetails);
        Assertions.assertEquals("This contact number is already taken!", response.getBody().getMessage());
        Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
    }

    @Test
    public void registrationServiceExistingExtIdTest() {
        UserDetails userDetails = getUserDetails();
        Mockito.when(repository.existsByExtId(userDetails.getExtId())).thenReturn(Boolean.TRUE);
        ResponseEntity<ResponseMessage> response = this.userService.registrationService(userDetails);
        Assertions.assertEquals("We have another user with same Id!", response.getBody().getMessage());
        Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());

    }

    @Test
    public void registrationServiceNewUserTest() {
        UserDetails userDetails = getUserDetails();
        Mockito.when(authService.saveAuth(any(Auth.class))).thenReturn(getUser().getAuth());
        Mockito.when(roleService.getRoleByName(userDetails.getRole())).thenReturn(getUser().getRole());
        Mockito.when(this.userService.saveUser(any(User.class))).thenReturn(getUser());
        ResponseEntity<ResponseMessage> response = this.userService.registrationService(userDetails);
        Assertions.assertEquals("Registration Successful!", response.getBody().getMessage());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void loadUserByUsernameTest(){
        Mockito.when(repository.findByExtId(anyString())).thenReturn(getUser());
        UserDetailsSecurity userSecurity = this.userService.loadUserByUsername(getUser().getExtId());
        Assertions.assertNotNull(userSecurity);
    }
}
