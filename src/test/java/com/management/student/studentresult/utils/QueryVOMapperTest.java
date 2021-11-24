package com.management.student.studentresult.utils;

import com.management.student.studentresult.enums.QueryParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class QueryVOMapperTest {

    @Test
    public void mapFromRequestParameterTest(){
        String roll = "MT2020000";
        String subjectCode = "AOS10";
        String subjectName = "Subject";
        Map<String, String> requestParam = new HashMap<>();
        requestParam.put(QueryParameters.ROLL_NUMBER.getParam(), roll);
        Assertions.assertEquals(roll, QueryVOMapper.mapFromRequestParameter(requestParam).getRollNumber());
        requestParam.put(QueryParameters.SUBJECT_CODE.getParam(), subjectCode);
        Assertions.assertEquals(subjectCode, QueryVOMapper.mapFromRequestParameter(requestParam).getSubjectCode());
        requestParam.put(QueryParameters.YEAR.getParam(), "2011");
        Assertions.assertEquals(2011, QueryVOMapper.mapFromRequestParameter(requestParam).getYear());
        requestParam.put(QueryParameters.TERM.getParam(), "1");
        Assertions.assertEquals(1, QueryVOMapper.mapFromRequestParameter(requestParam).getTerm());
        requestParam.put(QueryParameters.SUBJECT_NAME.getParam(), subjectName);
        Assertions.assertEquals(subjectName, QueryVOMapper.mapFromRequestParameter(requestParam).getSubjectName());

    }

    @Test
    public void mapFromRequestParameterInvalidTest() {
        Map<String, String> requestParam = new HashMap<>();
        requestParam.put(QueryParameters.YEAR.getParam(), "abc");
        Assertions.assertEquals(0, QueryVOMapper.mapFromRequestParameter(requestParam).getYear());
        requestParam.put(QueryParameters.TERM.getParam(), "abx");
        Assertions.assertEquals(0, QueryVOMapper.mapFromRequestParameter(requestParam).getTerm());

    }

    }
