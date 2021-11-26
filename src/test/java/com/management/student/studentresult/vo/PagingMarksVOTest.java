package com.management.student.studentresult.vo;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class PagingMarksVOTest {
    private PagingMarksVO pg;
    @Before
    public void setPg(){
        pg=new PagingMarksVO();
    }
    @Test
    public void test_CurrPage(){
        pg.setCurrentPage(55);
        assertTrue(pg.getCurrentPage()==55);
    }
    @Test
    public void test_TotalPage(){
        pg.setTotalPage(654);
        assertTrue(pg.getTotalPage()==654);
    }
    @Test
    public void test_PageSize(){
        pg.setPageSize(23);
        assertTrue(pg.getPageSize()==23);
    }
    @Test
    public void test_MarksVOlist(){
        List<MarksVO> marks=new ArrayList<MarksVO>(2);
        marks.add( new MarksVO("MT2020136", "KS101", "Physics", 2017, 2, 150, 105.234, "B"));
        marks.add(new MarksVO("MT2020137", "KS102", "Chemistry", 2017, 2, 150, 125.24, "B+"));
        pg.setMarksVOList(marks);
        assertTrue(pg.getMarksVOList()==marks);
    }
}
