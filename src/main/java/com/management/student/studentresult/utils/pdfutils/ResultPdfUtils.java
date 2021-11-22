package com.management.student.studentresult.utils.pdfutils;

import com.management.student.studentresult.enums.ResultComponentEnum;
import com.management.student.studentresult.utils.CommonUtils;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.UserDetails;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Component
public class ResultPdfUtils {

    public LinkedHashMap<ResultComponentEnum, String> createBasicDetails(UserDetails userDetails){
        LinkedHashMap<ResultComponentEnum, String> basicDetailInfo = new LinkedHashMap<>();
        basicDetailInfo.put(ResultComponentEnum.BASIC_INFO_NAME, userDetails.getName());
        basicDetailInfo.put(ResultComponentEnum.BASIC_INFO_ROLLNUMBER, userDetails.getExtId());
        basicDetailInfo.put(ResultComponentEnum.BASIC_INFO_DOB, CommonUtils.formatDate(userDetails.getDob()));
//        basicDetailInfo.put(ResultComponentEnum.BASIC_INFO_DOB,userDetails.getDob());
        basicDetailInfo.put(ResultComponentEnum.BASIC_INFO_EMAIL, userDetails.getEmail());
        basicDetailInfo.put(ResultComponentEnum.BASIC_INFO_CONTACT, String.valueOf(userDetails.getContactno()));
        basicDetailInfo.put(ResultComponentEnum.BASIC_INFO_ADDRESS, String.valueOf(userDetails.getAddress()));
        return basicDetailInfo;
    }

    public TreeMap<Integer, TreeMap<Integer, List<MarksVO>>> createMarksDetails(List<MarksVO> marksVOList){
        TreeMap<Integer, TreeMap<Integer, List<MarksVO>>> resultDetails = marksVOList.stream()
                .collect(Collectors.groupingBy(MarksVO::getYear, TreeMap::new,
                        Collectors.groupingBy(MarksVO::getTerm, TreeMap::new, Collectors.toList())));
        return resultDetails;
    }
}
