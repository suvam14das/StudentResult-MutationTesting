package com.management.student.studentresult.dao;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class SubjectTest {
    private Subject sbj;
    @Before
    public void setSbj(){
        sbj=new Subject();
    }
    @Test
    public void test_id(){
        sbj.setSubId(23);
        assertTrue(sbj.getSubId()==23);
    }
    @Test
    public void test_name(){
        sbj.setName("mathS");
        assertTrue(sbj.getName()=="mathS");
    }
    @Test
    public void test_subCode(){
        sbj.setSubCode("KS101");
        assertTrue(sbj.getSubCode()=="KS101");
    }
    @Test
    public void test_totScore(){
        sbj.setTotScore(155);
        assertTrue(sbj.getTotScore()==155);
    }
    @Test
    public void test_year(){
        sbj.setYear(2002);
        assertTrue(sbj.getYear()==2002);
    }
    @Test
    public void test_term(){
        sbj.setTerm(2);
        assertTrue(sbj.getTerm()==2);
    }
    @Test
    public void test_status() {
        sbj.setStatus("Pass");
        assertTrue(sbj.getStatus() == "Pass");
    }

    @Test
    public void test_CreatedBy() {
        User usr = new User();
        sbj.setCreatedBy(usr);
        assertTrue(sbj.getCreatedBy() == usr);
    }

    @Test
    public void test_CreatedAt() {
        Date dt = new Date();
        sbj.setCreatedAt(dt);
        assertTrue(sbj.getCreatedAt() == dt);
    }

    @Test
    public void test_ModifiedBy() {
        User usr = new User();
        sbj.setModifiedBy(usr);
        assertTrue(sbj.getModifiedBy() == usr);
    }

    @Test
    public void test_ModifiedAt() {
        Date dt = new Date();
        sbj.setModifiedAt(dt);
        assertTrue(sbj.getModifiedAt() == dt);
    }
}
