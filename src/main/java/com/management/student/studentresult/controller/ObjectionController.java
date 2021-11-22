package com.management.student.studentresult.controller;

import com.management.student.studentresult.dao.Objection;
import com.management.student.studentresult.service.MarksService;
import com.management.student.studentresult.service.ModeratorService;
import com.management.student.studentresult.service.ObjectionService;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.ObjectionVO;
import com.management.student.studentresult.vo.PagingObjectionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/objection")
@CrossOrigin("*")
public class ObjectionController {

    @Autowired
    private ModeratorService moderatorService;

    @Autowired
    private MarksService marksService;

    @Autowired
    private ObjectionService objectionService;

    @RequestMapping(value = "/raiseObjection", method = RequestMethod.POST)
    public ResponseEntity<?> studentRaiseObjection(@RequestBody List<MarksVO> marksVO) {
        List<Objection> objection;
        try {
            objection = objectionService.raiseObjection(marksVO);
        } catch (Exception ex) {
            String res = ex.getMessage();
            return new ResponseEntity<String>(res, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Objection>>(objection, HttpStatus.OK);
    }

    @RequestMapping(value = "/resolveObjection", method = RequestMethod.POST)
    public ResponseEntity<?> resolveRaisedObjection(@RequestBody List<ObjectionVO> objectionVOS) {
        List<ObjectionVO> objection;
        try {
            objection = objectionService.resolveObjection(objectionVOS);
        } catch (Exception ex) {
            String res = ex.getMessage();
            return new ResponseEntity<String>(res, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<ObjectionVO>>(objection, HttpStatus.OK);
    }

    @GetMapping("/studentObjections")
    public ResponseEntity<?> getStudentsObjection(@RequestParam Map<String, String> requestParams) {
        PagingObjectionVO response;
        try {
            String extId = requestParams.get("extId");
            int page = Integer.parseInt(requestParams.get("page"));
            int items = Integer.parseInt(requestParams.get("items"));
            Pageable pageable = PageRequest.of(page, items, Sort.by("createdBy").descending());
            response = objectionService.getObjections(extId, pageable);
        } catch (Exception ex) {
            String res = ex.getMessage();
            return new ResponseEntity<String>(res, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PagingObjectionVO>(response, HttpStatus.OK);
    }

    @GetMapping("/modObjections")
    public ResponseEntity<?> getModeratorsObjection(@RequestParam Map<String, String> requestParams) {
        PagingObjectionVO response;
        try {
            String extId = requestParams.get("extId");
            int page = Integer.parseInt(requestParams.get("page"));
            int items = Integer.parseInt(requestParams.get("items"));
            Pageable pageable = PageRequest.of(page, items, Sort.by("createdAt").descending());
            response = objectionService.getModObjections(extId, pageable);
        } catch (Exception ex) {
            String res = ex.getMessage();
            return new ResponseEntity<String>(res, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PagingObjectionVO>(response, HttpStatus.OK);
    }


}
