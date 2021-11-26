package com.management.student.studentresult.vo;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueryVOTest {
    private QueryVO query;
    @Before
    public void setQ(){
        query=new QueryVO();
    }
    @Test
    public void test_roll(){
        query.setRollNumber("MT2020136");
        assertTrue(query.getRollNumber()=="MT2020136");
    }
    @Test
    public void test_queryCode(){
        query.setSubjectCode("KS101");
        assertTrue(query.getSubjectCode()=="KS101");
    }
    @Test
    public void test_queryName(){
        query.setSubjectName("Science");
        assertTrue(query.getSubjectName()=="Science");
    }
    @Test
    public void test_year(){
        query.setYear(2002);
        assertTrue(query.getYear()==2002);
    }
    @Test
    public void test_term(){
        query.setTerm(2);
        assertTrue(query.getTerm()==2);
    }
}
