package com.management.student.studentresult.controller;

import com.management.student.studentresult.service.MarksService;
import com.management.student.studentresult.utils.QueryVOMapper;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.PagingMarksVO;
import com.management.student.studentresult.vo.QueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class MarksController {

    @Autowired
    private MarksService marksService;

    @GetMapping("/getMarks/pagination")
    public ResponseEntity<?> queryMarksPagination(@RequestParam Map<String, String> requestParameters){
        try {
            int page = Integer.parseInt(requestParameters.get("page"));
            int items = Integer.parseInt(requestParameters.get("items"));
            QueryVO query = QueryVOMapper.mapFromRequestParameter(requestParameters);
            Pageable pageable = PageRequest.of(page, items, Sort.by("createdAt").descending());
            PagingMarksVO response = marksService.getMarksDetailsWithPagination(query, pageable);
            if(response.getMarksVOList().size() == 0 )
                throw new Exception("No marks found !");
            return new ResponseEntity<PagingMarksVO>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/marks")
    public ResponseEntity<?> queryMarksOnParam(@RequestParam Map<String, String> requestParameters){
        QueryVO query = QueryVOMapper.mapFromRequestParameter(requestParameters);
        try {
            List<MarksVO> response =  marksService.getMarksDetailsWithoutPagination(query);
            if(response.size() == 0 )
                throw new Exception("No marks found !");
            return new ResponseEntity<List<MarksVO>>(response, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
