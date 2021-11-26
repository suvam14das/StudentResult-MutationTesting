package com.management.student.studentresult.dao;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ObjectionTest {
    private Objection obj;
    @Before
    public void setObj(){
        obj=new Objection();
    }
    @Test
    public void test_id(){
        obj.setObjId(234);
        assertTrue(obj.getObjId()==234);
    }
    @Test
    public void test_user(){
        User usr=new User();
        obj.setUser(usr);
        assertTrue(obj.getUser()==usr);
    }
    @Test
    public void test_marks(){
        Marks mrk=new Marks();
        obj.setMarks(mrk);
        assertTrue(obj.getMarks()==mrk);
    }
    @Test
    public void test_comment(){
        obj.setComment("Good");
        assertTrue(obj.getComment()=="Good");
    }
    @Test
    public void test_resolverId(){
        User usr=new User();
        obj.setResolverId(usr);
        assertTrue(obj.getResolverId()==usr);
    }
    @Test
    public void test_status() {
        obj.setStatus("Pass");
        assertTrue(obj.getStatus() == "Pass");
    }

    @Test
    public void test_CreatedBy() {
        User usr = new User();
        obj.setCreatedBy(usr);
        assertTrue(obj.getCreatedBy() == usr);
    }

    @Test
    public void test_CreatedAt() {
        Date dt = new Date();
        obj.setCreatedAt(dt);
        assertTrue(obj.getCreatedAt() == dt);
    }

    @Test
    public void test_ModifiedBy() {
        User usr = new User();
        obj.setModifiedBy(usr);
        assertTrue(obj.getModifiedBy() == usr);
    }

    @Test
    public void test_ModifiedAt() {
        Date dt = new Date();
        obj.setModifiedAt(dt);
        assertTrue(obj.getModifiedAt() == dt);
    }
}
