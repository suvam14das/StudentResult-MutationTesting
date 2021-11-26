package com.management.student.studentresult.vo;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResponseMessageTest {
    private ResponseMessage res;
    @Before
    public void setRes(){
        res=new ResponseMessage("Ok");
    }
    @Test
    public void test_msg(){
        res.setMessage("Hello");
        assertTrue(res.getMessage()=="Hello");
    }
}
