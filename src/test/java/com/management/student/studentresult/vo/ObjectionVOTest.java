package com.management.student.studentresult.vo;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ObjectionVOTest {
    private ObjectionVO ob;
    @Before
    public void setOb(){
        ob=new ObjectionVO();
    }
    @Test
    public void test_Opr(){
        ob.setOperation("None");
        assertTrue(ob.getOperation()=="None");
    }
    @Test
    public void test_Comm(){
        ob.setComments("Good");
        assertTrue(ob.getComments()=="Good");
    }
}
