package com.management.student.studentresult.dao;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class RoleActionTest {
    private RoleAction r;
    @Before
    public void setR(){
        r=new RoleAction();
    }
    @Test
    public void test_id(){
        r.setRlacId(1234);
        assertTrue(r.getRlacId()==1234);
    }
    @Test
    public void test_role(){
        Role l=new Role();
        r.setRole(l);
        assertTrue(r.getRole()==l);
    }
    @Test
    public void test_action(){
        Action ac=new Action();
        r.setAction(ac);
        assertTrue(r.getAction()==ac);
    }
    @Test
    public void test_Access(){
        r.setAccess("Yes");
        assertTrue(r.getAccess()=="Yes");
    }
    @Test
    public void test_status() {
        r.setStatus("Pass");
        assertTrue(r.getStatus() == "Pass");
    }

    @Test
    public void test_CreatedBy() {
        User usr = new User();
        r.setCreatedBy(usr);
        assertTrue(r.getCreatedBy() == usr);
    }

    @Test
    public void test_CreatedAt() {
        Date dt = new Date();
        r.setCreatedAt(dt);
        assertTrue(r.getCreatedAt() == dt);
    }

    @Test
    public void test_ModifiedBy() {
        User usr = new User();
        r.setModifiedBy(usr);
        assertTrue(r.getModifiedBy() == usr);
    }

    @Test
    public void test_ModifiedAt() {
        Date dt = new Date();
        r.setModifiedAt(dt);
        assertTrue(r.getModifiedAt() == dt);
    }
}
