package com.management.student.studentresult.dao;

import static org.junit.Assert.assertTrue;

import com.management.student.studentresult.dao.Action;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ActionTest {
    private Action act;

    @Before
    public void setup() {
        act = new Action();
    }

    @Test
    public void test_name() {
        act.setName("best");
        assertTrue(act.getName() == "best");
    }

    @Test
    public void test_id() {
        act.setActId(234);
        assertTrue(act.getActId() == 234);
    }

    @Test
    public void test_status() {
        act.setStatus("Pass");
        assertTrue(act.getStatus() == "Pass");
    }

    @Test
    public void test_CreatedBy() {
        User usr = new User();
        act.setCreatedBy(usr);
        assertTrue(act.getCreatedBy() == usr);
    }

    @Test
    public void test_CreatedAt() {
        Date dt = new Date();
        act.setCreatedAt(dt);
        assertTrue(act.getCreatedAt() == dt);
    }

    @Test
    public void test_ModifiedBy() {
        User usr = new User();
        act.setModifiedBy(usr);
        assertTrue(act.getModifiedBy() == usr);
    }

    @Test
    public void test_ModifiedAt() {
        Date dt = new Date();
        act.setModifiedAt(dt);
        assertTrue(act.getModifiedAt() == dt);
    }
}
