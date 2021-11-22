package com.management.student.studentresult.utils;

import com.management.student.studentresult.enums.QueryParameters;
import com.management.student.studentresult.vo.QueryVO;

import java.util.Map;

public class QueryVOMapper {
    public static QueryVO mapFromRequestParameter(Map<String, String> requestParam){
        QueryVO queryVO = new QueryVO();
        if(requestParam.containsKey(QueryParameters.ROLL_NUMBER.getParam())){
            queryVO.setRollNumber(requestParam.get(QueryParameters.ROLL_NUMBER.getParam()));
        }
        if(requestParam.containsKey(QueryParameters.SUBJECT_CODE.getParam())){
            queryVO.setSubjectCode(requestParam.get(QueryParameters.SUBJECT_CODE.getParam()));
        }
        try {
            if (requestParam.containsKey(QueryParameters.TERM.getParam())) {
                queryVO.setTerm(Integer.parseInt(requestParam.get(QueryParameters.TERM.getParam())));
            }
        }catch(NumberFormatException e ){
            queryVO.setTerm(0);
        }

        try {
            if (requestParam.containsKey(QueryParameters.YEAR.getParam())) {
                queryVO.setYear(Integer.parseInt(requestParam.get(QueryParameters.YEAR.getParam())));
            }
        }catch(NumberFormatException e ){
            queryVO.setYear(0);
        }

        if(requestParam.containsKey(QueryParameters.SUBJECT_NAME.getParam())){
            queryVO.setSubjectName(requestParam.get(QueryParameters.SUBJECT_NAME.getParam()));
        }
        return queryVO;
    }
}
