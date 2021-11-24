package com.management.student.studentresult.utils.pdfutils;

import com.management.student.studentresult.enums.ResultComponentEnum;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class ResultPdfUtilsTest {
    private ResultPdfUtils resultPdfUtils;
    @BeforeEach
    public void init(){
        this.resultPdfUtils = new ResultPdfUtils();
    }

    @Test
    public void createBasicDetailsTest(){
        Date dob = new Date();
        UserDetails userDetails = new UserDetails("Student Name", "MT20200000", dob, "student Address", "9999999999", "student@gmail.com", "pass", -1);
        LinkedHashMap<ResultComponentEnum, String> data =  this.resultPdfUtils.createBasicDetails(userDetails);
        Assertions.assertEquals(userDetails.getName(), data.get(ResultComponentEnum.BASIC_INFO_NAME));
        Assertions.assertEquals(userDetails.getExtId(), data.get(ResultComponentEnum.BASIC_INFO_ROLLNUMBER));
        Assertions.assertEquals(userDetails.getDob(), dob);
        Assertions.assertEquals(userDetails.getAddress(), data.get(ResultComponentEnum.BASIC_INFO_ADDRESS));
        Assertions.assertEquals(userDetails.getContactno(), data.get(ResultComponentEnum.BASIC_INFO_CONTACT));
        Assertions.assertEquals(userDetails.getEmail(), data.get(ResultComponentEnum.BASIC_INFO_EMAIL));
    }

    @Test
    public void createMarksDetailsTest(){
        List<MarksVO> marksVOList = IntStream.range(0, 7).mapToObj(x -> {
            MarksVO marksVO = new MarksVO();
            marksVO.setRollNo("MT2020"+x);
            marksVO.setSubjectName("Sub"+x);
            marksVO.setSubjectCode("Code"+x);
            marksVO.setTotalMarks(100);
            marksVO.setMarksObtained(100.00-2*x);
            marksVO.setTerm(x%2+1);
            marksVO.setYear(2000+x/4);
            marksVO.setGrade("A");
            return marksVO;
        } ).collect(Collectors.toList());

        List<MarksVO> marksVO2000_1 = marksVOList.stream()
                .filter( marks -> marks.getYear() == 2000 && marks.getTerm() == 1)
                .collect(Collectors.toList());
        List<MarksVO> marksVO2000_2 = marksVOList.stream()
                .filter( marks -> marks.getYear() == 2000 && marks.getTerm() == 2)
                .collect(Collectors.toList());
        List<MarksVO> marksVO2001_1 = marksVOList.stream()
                .filter( marks -> marks.getYear() == 2001 && marks.getTerm() == 1)
                .collect(Collectors.toList());
        List<MarksVO> marksVO2001_2 = marksVOList.stream()
                .filter( marks -> marks.getYear() == 2001 && marks.getTerm() == 2)
                .collect(Collectors.toList());

        TreeMap<Integer, TreeMap<Integer, List<MarksVO>>> details = this.resultPdfUtils.createMarksDetails(marksVOList);
        Assertions.assertEquals(marksVO2000_1.size(), details.get(2000).get(1).size());
        Assertions.assertEquals(marksVO2000_2.size(), details.get(2000).get(2).size());
        Assertions.assertEquals(marksVO2001_1.size(), details.get(2001).get(1).size());
        Assertions.assertEquals(marksVO2001_2.size(), details.get(2001).get(2).size());
    }

}
