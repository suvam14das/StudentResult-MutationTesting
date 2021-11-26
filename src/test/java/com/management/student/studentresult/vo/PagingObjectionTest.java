package com.management.student.studentresult.vo;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PagingObjectionTest {
    private PagingObjectionVO pg;
    @Before
    public void setPg(){
        pg=new PagingObjectionVO();
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
    public void test_VOList(){
        List<ObjectionVO> v=new ArrayList<ObjectionVO>();
        v.add(new ObjectionVO());
        pg.setObjectionVOList(v);
        assertTrue(pg.getObjectionVOList()==v);
    }
}
