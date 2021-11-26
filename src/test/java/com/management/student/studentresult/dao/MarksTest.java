package com.management.student.studentresult.dao;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class MarksTest {
    private Marks mrk;
    @Before
    public void setMrk(){
        mrk=new Marks();
    }
    @Test
    public void test_id(){
        mrk.setMrkId(12435);
        assertTrue(mrk.getMrkId()==12435);
    }
    @Test
    public void test_user(){
        User usr=new User();
        mrk.setUser(usr);
        assertTrue(mrk.getUser()==usr);
    }
    @Test
    public void test_Subject(){
        Subject sbj=new Subject();
        mrk.setSubject(sbj);
        assertTrue(mrk.getSubject()==sbj);
    }
    @Test
    public void test_score(){
        mrk.setScore(63.456);
        assertTrue(mrk.getScore()==63.456);
    }
    @Test
    public void test_totScore(){
        mrk.setTotScore(155);
        assertTrue(mrk.getTotScore()==155);
    }
    @Test
    public void test_year(){
        mrk.setYear(2002);
        assertTrue(mrk.getYear()==2002);
    }
    @Test
    public void test_grad(){
        mrk.setGrade("A");
        assertTrue(mrk.getGrade()=="A");
    }
    @Test
    public void test_term(){
        mrk.setTerm(2);
        assertTrue(mrk.getTerm()==2);
    }
    @Test
    public void test_status() {
        mrk.setStatus("Pass");
        assertTrue(mrk.getStatus() == "Pass");
    }

    @Test
    public void test_CreatedBy() {
        User usr = new User();
        mrk.setCreatedBy(usr);
        assertTrue(mrk.getCreatedBy() == usr);
    }

    @Test
    public void test_CreatedAt() {
        Date dt = new Date();
        mrk.setCreatedAt(dt);
        assertTrue(mrk.getCreatedAt() == dt);
    }

    @Test
    public void test_ModifiedBy() {
        User usr = new User();
        mrk.setModifiedBy(usr);
        assertTrue(mrk.getModifiedBy() == usr);
    }

    @Test
    public void test_ModifiedAt() {
        Date dt = new Date();
        mrk.setModifiedAt(dt);
        assertTrue(mrk.getModifiedAt() == dt);
    }
}
