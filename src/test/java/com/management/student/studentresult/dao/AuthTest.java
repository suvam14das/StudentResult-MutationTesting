package com.management.student.studentresult.dao;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class AuthTest {
    private Auth ath;
    @Before
    public void setup(){
        ath=new Auth();
    }
    @Test
    public void test_AuthId(){
        ath.setAuthId(2345);
        assertTrue(ath.getAuthId()==2345);
    }
    @Test
    public void test_Email(){
        ath.setEmail("bhash@gmail.com");
        assertTrue(ath.getEmail()=="bhash@gmail.com");
    }
    @Test
    public void test_Password(){
        ath.setPassword("123abc");
        assertTrue(ath.getPassword()=="123abc");
    }
    @Test
    public void test_status(){
        ath.setStatus("Pass");
        assertTrue(ath.getStatus()=="Pass");
    }
    @Test
    public void test_CreatedBy(){
        User usr=new User();
        ath.setCreatedBy(usr);
        assertTrue(ath.getCreatedBy()==usr);
    }
    @Test
    public void test_CreatedAt(){
        Date dt=new Date();
        ath.setCreatedAt(dt);
        assertTrue(ath.getCreatedAt()==dt);
    }
    @Test
    public void test_ModifiedBy(){
        User usr=new User();
        ath.setModifiedBy(usr);
        assertTrue(ath.getModifiedBy()==usr);
    }
    @Test
    public void test_ModifiedAt(){
        Date dt=new Date();
        ath.setModifiedAt(dt);
        assertTrue(ath.getModifiedAt()==dt);
    }
}
