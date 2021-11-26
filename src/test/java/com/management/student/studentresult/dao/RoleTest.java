package com.management.student.studentresult.dao;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class RoleTest {
    private Role r;
    @Before
    public void setR(){
        r=new Role();
    }
    @Test
    public void test_id(){
        r.setRoleId(765);
        assertTrue(r.getRoleId()==765);
    }
    @Test
    public void test_name(){
        r.setName("Actor");
        assertTrue(r.getName()=="Actor");
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
