package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.*;
import com.management.student.studentresult.enums.Operation;
import com.management.student.studentresult.repository.MarksRepository;
import com.management.student.studentresult.repository.ObjectionRepository;
import com.management.student.studentresult.repository.SubjectRepository;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.ObjectionVO;
import com.management.student.studentresult.vo.PagingObjectionVO;
import com.management.student.studentresult.vo.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectionService {

    @Autowired
    ObjectionRepository objectionRepository;

    @Autowired
    UserService userService;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    MarksRepository repository;

    public List<Objection> raiseObjection(List<MarksVO> marksVOList){
        List<Objection> objectionList = new ArrayList<>();
        for(MarksVO marksVO : marksVOList) {
            Objection objection = new Objection();
            User user = userService.getUserByExtId(marksVO.getRollNo());
            objection.setCreatedBy(user);
            Subject subject = subjectRepository.findBySubCode(marksVO.getSubjectCode());
            Marks mark = repository.findByUserAndSubjectAndTermAndYear(user, subject, marksVO.getTerm(), marksVO.getYear());
            objection.setMarks(mark);
            objectionRepository.save(objection);
            String response = "Objection raised Successfullly";
        }
        return objectionList;
    }

    public List<ObjectionVO> resolveObjection(List<ObjectionVO> objectionVOS) {
        List<ObjectionVO> objectionVOList = new ArrayList<>();
        for (ObjectionVO objection : objectionVOS) {
            User user = userService.getUserByExtId(objection.getRollNo());
            Subject subject = subjectRepository.findBySubCode(objection.getSubjectCode());
            Marks mark = repository.findByUserAndSubjectAndTermAndYear(user, subject, objection.getTerm(), objection.getYear());
            User moderator = userService.getUserById(mark.getCreatedBy().getUserId());
            Objection obj = objectionRepository.findByMarks(mark);
            obj.setComment(objection.getComments());
            obj.setResolverId(mark.getCreatedBy());
            if (objection.getOperation().equals("RESOLVED"))
                obj.setStatus("RESOLVED");
            else
                obj.setStatus("REJECTED");
            objectionRepository.save(obj);
            objectionVOList.add(objection);

        }
        return objectionVOList;
    }

    public PagingObjectionVO getObjections(String extId, Pageable pageable) {
        List<ObjectionVO> objectionVOList = new ArrayList<>();
        User user = userService.getUserByExtId(extId);
        Page<Objection> objections = objectionRepository.findAllByCreatedBy(user, pageable);
        for (Objection objection : objections) {
            ObjectionVO objectionVO = new ObjectionVO();
            objectionVO.setComments(objection.getComment());
            objectionVO.setOperation(objection.getStatus());
            objectionVO.setGrade(objection.getMarks().getGrade());
            objectionVO.setRollNo(objection.getCreatedBy().getExtId());
            objectionVO.setYear(objection.getMarks().getYear());
            objectionVO.setTerm(objection.getMarks().getTerm());
            objectionVO.setSubjectCode(objection.getMarks().getSubject().getSubCode());
            objectionVO.setTotalMarks(objection.getMarks().getTotScore());
            objectionVO.setMarksObtained(objection.getMarks().getScore());
            objectionVO.setSubjectName(objection.getMarks().getSubject().getName());
            objectionVOList.add(objectionVO);
        }

        return new PagingObjectionVO(pageable.getPageNumber(), objections.getTotalPages(), pageable.getPageSize(), objectionVOList);
    }

    public PagingObjectionVO getModObjections(String extId, Pageable pageable) {
        List<ObjectionVO> objectionVOList = new ArrayList<>();
        User user = userService.getUserByExtId(extId);
        Page<Objection> objections = objectionRepository.findAllByResolverId(user, pageable);
        for (Objection objection : objections) {
            if (objection.getStatus()==null || objection.getStatus().isEmpty()) {
                ObjectionVO objectionVO = new ObjectionVO();
                Subject subject = subjectRepository.findBySubCode(objection.getMarks().getSubject().getSubCode());
                Marks mark = repository.findByUserAndSubjectAndTermAndYear(user, subject, objection.getMarks().getTerm(), objection.getMarks().getYear());
                objectionVO.setComments(objection.getComment());
                objectionVO.setOperation(objection.getStatus());
                objectionVO.setGrade(objection.getMarks().getGrade());
                objectionVO.setRollNo(objection.getCreatedBy().getExtId());
                objectionVO.setYear(objection.getMarks().getYear());
                objectionVO.setTerm(objection.getMarks().getTerm());
                objectionVO.setSubjectCode(objection.getMarks().getSubject().getSubCode());
                objectionVO.setTotalMarks(objection.getMarks().getTotScore());
                objectionVO.setMarksObtained(objection.getMarks().getScore());
                objectionVO.setSubjectName(objection.getMarks().getSubject().getName());
                objectionVOList.add(objectionVO);
            }
        }

        return new PagingObjectionVO(pageable.getPageNumber(), objections.getTotalPages(), pageable.getPageSize(), objectionVOList);
    }
}
