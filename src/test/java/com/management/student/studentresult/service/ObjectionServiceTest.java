package com.management.student.studentresult;

import com.management.student.studentresult.dao.Marks;
import com.management.student.studentresult.dao.Objection;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.repository.MarksRepository;
import com.management.student.studentresult.repository.ObjectionRepository;
import com.management.student.studentresult.repository.UserRepository;
import com.management.student.studentresult.service.MarksService;
import com.management.student.studentresult.service.ObjectionService;
import com.management.student.studentresult.service.UserService;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.ObjectionVO;
import com.management.student.studentresult.vo.PagingObjectionVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.AutoConfigureDataR2dbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@Transactional
public class ObjectionServiceTest {

    @Autowired
    private ObjectionService objectionService;
    @Autowired
    private MarksRepository marksRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectionRepository objectionRepository;


    @Test
    public void raiseObjectionsTest(){
        Marks marks = marksRepository.findAll().get(0);
        MarksVO marksVO = new MarksVO(marks.getUser().getExtId(),marks.getSubject().getSubCode(),marks.getSubject().getName(),marks.getYear(),marks.getTerm(), marks.getTotScore(), marks.getScore(), marks.getGrade());
        Objection objection = objectionService.raiseObjection(marksVO);

        assertNotEquals(null, objection);
        assertEquals(userService.getUserByExtId(marksVO.getRollNo()), objection.getCreatedBy());
        assertEquals(marks, objection.getMarks());
    }

    @Test
    public void resolveObjectionsTest(){
        Objection objection = objectionRepository.findAll().get(0);
        Marks marks = objection.getMarks();
        ObjectionVO objectionVOInput = new ObjectionVO(objection.getCreatedBy().getExtId(), marks.getSubject().getSubCode(), marks.getSubject().getName(), marks.getYear(), marks.getTerm(), marks.getTotScore(), marks.getScore(),"","", marks.getGrade());
        objectionVOInput.setOperation("RESOLVED");
        objectionVOInput.setComments("reevaluated");
        ObjectionVO objectionVOOutput = objectionService.resolveObjection(objectionVOInput);

        assertNotEquals(null, objectionVOOutput);
        assertEquals("RESOLVED", objection.getStatus());
        assertEquals("reevaluated", objection.getComment());
        assertEquals(marks.getCreatedBy(), objection.getResolverId());
    }

    @Test
    public void rejectObjectionsTest(){
        Objection objection = objectionRepository.findAll().get(0);
        Marks marks = objection.getMarks();
        ObjectionVO objectionVOInput = new ObjectionVO(objection.getCreatedBy().getExtId(), marks.getSubject().getSubCode(), marks.getSubject().getName(), marks.getYear(), marks.getTerm(), marks.getTotScore(), marks.getScore(), "","",  marks.getGrade());
        objectionVOInput.setOperation("REJECTED");
        objectionVOInput.setComments("reevaluated");
        ObjectionVO objectionVOOutput = objectionService.resolveObjection(objectionVOInput);

        assertNotEquals(null, objectionVOOutput);
        assertEquals("REJECTED", objection.getStatus());
        assertEquals("reevaluated", objection.getComment());
        assertEquals(marks.getCreatedBy(), objection.getResolverId());
    }

    @Test
    public void getObjectionsTest(){
        PagingObjectionVO pagingObjectionVO = objectionService.getObjections("MT2020093", PageRequest.of(0,1));
        assertNotEquals(null, pagingObjectionVO);

    }

    @Test
    public void getModObjectionsTest(){
        Objection objection = objectionRepository.findAll().get(0);
        objection.setStatus(null);
        objectionRepository.save(objection);
        PagingObjectionVO pagingObjectionVO = objectionService.getModObjections("MT2020155", PageRequest.of(0,1));
        System.out.println(pagingObjectionVO.getObjectionVOList().size());
        assertNotEquals(null, pagingObjectionVO);
    }

}
