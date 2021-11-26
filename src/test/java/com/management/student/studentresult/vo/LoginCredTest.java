package com.management.student.studentresult.vo;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginCredTest {
    private LoginCredentials lo;
    @Before
    public void setLo(){
        lo=new LoginCredentials("bhash","123abc");
    }
    @Test
    public void test_username(){
        lo.setUsername("Aditya");
        assertTrue(lo.getUsername()=="Aditya");
    }
    @Test
    public void test_pass(){
        lo.setPassword("abcdefg123");
        assertTrue(lo.getPassword()=="abcdefg123");
    }
}
