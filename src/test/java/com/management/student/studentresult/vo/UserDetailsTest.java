package com.management.student.studentresult.vo;
import static org.junit.Assert.assertTrue;

import com.management.student.studentresult.dao.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class UserDetailsTest {
    private UserDetails u;
    @Before
    public void setUsr(){
        u=new UserDetails();
    }
    @Test
    public void test_address(){
        u.setAddress("Raebareli-229010,UP");
        assertTrue(u.getAddress()=="Raebareli-229010,UP");
    }
    @Test
    public void test_role(){
        u.setRole("Actor");
        assertTrue(u.getRole()=="Actor");
    }
    @Test
    public void test_extId(){
        u.setExtId("9877");
        assertTrue(u.getExtId()=="9877");
    }
    @Test
    public void test_name(){
        u.setName("bhashkar");
        assertTrue(u.getName()=="bhashkar");
    }
    @Test
    public void test_phone(){
        u.setContactno("9569148710");
        assertTrue(u.getContactno()=="9569148710");
    }
    @Test
    public void test_dob(){
        Date d=new Date();
        u.setDob(d);
        assertTrue(u.getDob()==d);
    }
    @Test
    public void test_email(){
        u.setEmail("bhas@gmail.com");
        assertTrue(u.getEmail()=="bhas@gmail.com");
    }
    @Test
    public void test_pass(){
        u.setPassword("abc123");
        assertTrue(u.getPassword()=="abc123");
    }
    @Test
    public void test_gen(){
        u.setGender("M");
        assertTrue(u.getGender()=="M");
    }
    @Test
    public void test_RollId(){
        u.setRoleId(121);
        assertTrue(u.getRoleId()==121);
    }
}
