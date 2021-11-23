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

    public Objection raiseObjection(MarksVO marksVO){

            Objection objection = new Objection();
            User user = userService.getUserByExtId(marksVO.getRollNo());
            objection.setCreatedBy(user);
            Subject subject = subjectRepository.findBySubCode(marksVO.getSubjectCode());
            Marks mark = repository.findByUserAndSubjectAndTermAndYear(user, subject, marksVO.getTerm(), marksVO.getYear());
            objection.setMarks(mark);
            objectionRepository.save(objection);
            String response = "Objection raised Successfullly";
            return objection;
    }

    public ObjectionVO resolveObjection(ObjectionVO objection) {


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
        return objection;
    }

    public PagingObjectionVO getObjections(String extId, Pageable pageable) {
        List<ObjectionVO> objectionVOList = new ArrayList<>();
        User user = userService.getUserByExtId(extId);
        Page<Objection> objections = objectionRepository.findAllByCreatedBy(user, pageable);
        for (Objection objection : objections) {
            Marks marks = objection.getMarks();
            ObjectionVO objectionVO = new ObjectionVO(objection.getCreatedBy().getExtId(), marks.getSubject().getSubCode(), marks.getSubject().getName(), marks.getYear(), marks.getTerm(), marks.getTotScore(), marks.getScore(),objection.getComment(),objection.getStatus(), marks.getGrade());

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
                Marks marks = objection.getMarks();
                ObjectionVO objectionVO = new ObjectionVO(objection.getCreatedBy().getExtId(), marks.getSubject().getSubCode(), marks.getSubject().getName(), marks.getYear(), marks.getTerm(), marks.getTotScore(), marks.getScore(),objection.getComment(),objection.getStatus(), marks.getGrade());
                objectionVOList.add(objectionVO);
            }
        }

        return new PagingObjectionVO(pageable.getPageNumber(), objections.getTotalPages(), pageable.getPageSize(), objectionVOList);
    }
}
