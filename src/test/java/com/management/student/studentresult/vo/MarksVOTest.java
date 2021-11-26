package com.management.student.studentresult.vo;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MarksVOTest {
    private MarksVO mv;
    @Before
    public void setMv(){
        mv=new MarksVO();
    }
    @Test
    public void test_roll(){
        mv.setRollNo("MT2020136");
        assertTrue(mv.getRollNo()=="MT2020136");
    }
    @Test
    public void test_mvCode(){
        mv.setSubjectCode("KS101");
        assertTrue(mv.getSubjectCode()=="KS101");
    }
    @Test
    public void test_mvName(){
        mv.setSubjectName("Science");
        assertTrue(mv.getSubjectName()=="Science");
    }
    @Test
    public void test_year(){
        mv.setYear(2002);
        assertTrue(mv.getYear()==2002);
    }
    @Test
    public void test_term(){
        mv.setTerm(2);
        assertTrue(mv.getTerm()==2);
    }
    @Test
    public void test_total(){
        mv.setTotalMarks(600);
        assertTrue(mv.getTotalMarks()==600);
    }
    @Test
    public void test_markobt(){
        mv.setMarksObtained(480.2345);
        assertTrue(mv.getMarksObtained()==480.2345);
    }
    @Test
    public void test_grad(){
        mv.setGrade("B+");
        assertTrue(mv.getGrade()=="B+");
    }
    @Test
    public void test_opr(){
        mv.setOperation("none");
        assertTrue(mv.getOperation()=="none");
    }
    @Test
    public void test(){
        mv=new MarksVO("MT2020136","KS101","Physics",2017,2,150,105.234,"B");
        assertTrue(mv.toString().equals("MarksVO [rollNo=MT2020136, subjectCode=KS101, subjectName=Physics, year=2017, " +
                "term=2, totalMarks=150, marksObtained=105.234, grade=B]"));
    }
}
