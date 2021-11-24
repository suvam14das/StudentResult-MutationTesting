package com.management.student.studentresult.utils;

import com.management.student.studentresult.dao.Auth;
import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.dao.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;

@SpringBootTest
public class UserDetailsSecurityTest {

    private User getUser(){
        Role role = new Role();
        role.setName("STUDENT");
        Auth auth = new Auth();
        auth.setEmail("student@gmail.com");
        auth.setPassword("password");
        User user = new User();
        user.setAuth(auth);
        user.setExtId("MT0000000");
        user.setName("User Name");
        user.setStatus("ACTIVE");
        user.setRole(role);
        return user;
    }

    @Test
    public void getterSetterTestForValidUser(){
        User user = getUser();
        UserDetailsSecurity userDetailsSecurity = new UserDetailsSecurity(user);

        Assertions.assertEquals(user.getExtId(), userDetailsSecurity.getUsername());
        Assertions.assertEquals(user.getAuth().getPassword(), userDetailsSecurity.getPassword());
        Assertions.assertEquals(user.getName(), userDetailsSecurity.getName());
        Assertions.assertEquals(user.getRole().getName(), userDetailsSecurity.getRole());
        Assertions.assertEquals(new ArrayList<GrantedAuthority>(), userDetailsSecurity.getAuthorities());
        Assertions.assertTrue(userDetailsSecurity.isEnabled());
        Assertions.assertTrue(userDetailsSecurity.isAccountNonExpired());
        Assertions.assertTrue(userDetailsSecurity.isAccountNonLocked());
        Assertions.assertTrue(userDetailsSecurity.isCredentialsNonExpired());
        Assertions.assertEquals(1L, userDetailsSecurity.getSerialversionuid());
    }

    @Test
    public void getterSetterTestForInValidUser(){
        User user = getUser();
        user.setStatus("INACTIVE");
        UserDetailsSecurity userDetailsSecurity = new UserDetailsSecurity(user);

        Assertions.assertEquals(user.getExtId(), userDetailsSecurity.getUsername());
        Assertions.assertEquals(user.getAuth().getPassword(), userDetailsSecurity.getPassword());
        Assertions.assertEquals(user.getName(), userDetailsSecurity.getName());
        Assertions.assertEquals(user.getRole().getName(), userDetailsSecurity.getRole());
        Assertions.assertEquals(new ArrayList<GrantedAuthority>(), userDetailsSecurity.getAuthorities());
        Assertions.assertFalse(userDetailsSecurity.isEnabled());
        Assertions.assertFalse(userDetailsSecurity.isAccountNonExpired());
        Assertions.assertFalse(userDetailsSecurity.isAccountNonLocked());
        Assertions.assertFalse(userDetailsSecurity.isCredentialsNonExpired());
    }
}
