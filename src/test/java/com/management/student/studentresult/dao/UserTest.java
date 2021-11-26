package com.management.student.studentresult.dao;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class UserTest {
    private User u;
    @Before
    public void setU(){
        u=new User();
    }
    @Test
    public void test_id(){
        u.setUserId(123);
        assertTrue(u.getUserId()==123);
    }
    @Test
    public void test_auth(){
        Auth a=new Auth();
        u.setAuth(a);
        assertTrue(u.getAuth()==a);
    }
    @Test
    public void test_address(){
        u.setAddress("Raebareli-229010,UP");
        assertTrue(u.getAddress()=="Raebareli-229010,UP");
    }
    @Test
    public void test_role(){
        Role r=new Role();
        u.setRole(r);
        assertTrue(u.getRole()==r);
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
        u.setPhone("9569148710");
        assertTrue(u.getPhone()=="9569148710");
    }
    @Test
    public void test_dob(){
        Date d=new Date();
        u.setDob(d);
        assertTrue(u.getDob()==d);
    }
    @Test
    public void test_status() {
        u.setStatus("Pass");
        assertTrue(u.getStatus() == "Pass");
    }

    @Test
    public void test_CreatedBy() {
        User usr = new User();
        u.setCreatedBy(usr);
        assertTrue(u.getCreatedBy() == usr);
    }

    @Test
    public void test_CreatedAt() {
        Date dt = new Date();
        u.setCreatedAt(dt);
        assertTrue(u.getCreatedAt() == dt);
    }

    @Test
    public void test_ModifiedBy() {
        User usr = new User();
        u.setModifiedBy(usr);
        assertTrue(u.getModifiedBy() == usr);
    }

    @Test
    public void test_ModifiedAt() {
        Date dt = new Date();
        u.setModifiedAt(dt);
        assertTrue(u.getModifiedAt() == dt);
    }
}
