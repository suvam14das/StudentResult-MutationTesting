package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Marks;
import com.management.student.studentresult.repository.MarksRepository;
import com.management.student.studentresult.service.MarksService;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.QueryVO;
import org.apache.pdfbox.pdmodel.font.encoding.MacExpertEncoding;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MarksServiceTest {

    @Autowired
    private MarksService marksService;
    @Autowired
    private MarksRepository marksRepository;

    @Test
    public void saveMarksTest(){
        Marks marks = new Marks();
        assertNotEquals(null,marksService.saveMarks(marks));
    }

    @Test
    public void getMarksTest(){
        QueryVO queryVO = new QueryVO();
        Pageable pageable = PageRequest.of(0,5);
        assertNotEquals(Collections.emptyList(), marksService.getMarksDetailsWithoutPagination(queryVO));
        assertNotEquals(null, marksService.getMarksDetailsWithPagination(queryVO, pageable));
    }

    @Test
    public void updateMarksTest() throws ParseException {

        Marks marks = marksRepository.findAll().get(0);
        MarksVO marksVO = new MarksVO(marks.getUser().getExtId(),marks.getSubject().getSubCode(),marks.getSubject().getName(),marks.getYear(),marks.getTerm(), marks.getTotScore(), marks.getScore(), marks.getGrade());
        marksVO.setMarksObtained(91.0);
        marksVO.setGrade("A+");
        marksVO.setTotalMarks(150);
        marksVO.setOperation("UPDATE");
        List<MarksVO> marksVOList = new ArrayList<MarksVO>();
        marksVOList.add(marksVO);

        assertNotEquals(Collections.emptyList(), marksService.updateMarksQueryResult(marksVOList));

    }

    @Test
    public void performUpdateTest() throws ParseException {
        Marks marks = marksRepository.findAll().get(0);
        MarksVO marksVO = new MarksVO(marks.getUser().getExtId(),marks.getSubject().getSubCode(),marks.getSubject().getName(),marks.getYear(),marks.getTerm(), marks.getTotScore(), marks.getScore(), marks.getGrade());
        marksVO.setMarksObtained(91.0);
        marksVO.setGrade("A+");
        marksVO.setTotalMarks(150);
        marksVO.setOperation("UPDATE");
        MarksVO marksVOUpdated = marksService.performUpdate(marks,marksVO,marks.getSubject());

        assertNotEquals(null, marksVOUpdated);
        assertEquals(marksVO.getGrade(), marksVOUpdated.getGrade());
        assertEquals(marksVO.getMarksObtained(), marksVOUpdated.getMarksObtained());
        assertEquals(marksVO.getTotalMarks(), marksVOUpdated.getTotalMarks());
    }

    @Test
    public void deleteMarksTest() throws ParseException {

        Marks marks = marksRepository.findAll().get(0);
        MarksVO marksVO = new MarksVO(marks.getUser().getExtId(),marks.getSubject().getSubCode(),marks.getSubject().getName(),marks.getYear(),marks.getTerm(), marks.getTotScore(), marks.getScore(), marks.getGrade());
        marksVO.setOperation("DELETE");
        List<MarksVO> marksVOList = new ArrayList<MarksVO>();
        marksVOList.add(marksVO);

        assertNotEquals(Collections.emptyList(), marksService.updateMarksQueryResult(marksVOList));
    }

    @Test
    public void performSoftDeleteTest() throws ParseException {
        Marks marks = marksRepository.findAll().get(0);
        MarksVO marksVO = new MarksVO(marks.getUser().getExtId(),marks.getSubject().getSubCode(),marks.getSubject().getName(),marks.getYear(),marks.getTerm(), marks.getTotScore(), marks.getScore(), marks.getGrade());
        assertNotEquals(null, marksService.performSoftDelete(marks, marksVO, marks.getSubject()));
        assertEquals("INACTIVE", marks.getStatus());
    }
}
