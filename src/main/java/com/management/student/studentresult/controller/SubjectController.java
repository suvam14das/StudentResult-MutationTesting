package com.management.student.studentresult.controller;

import com.management.student.studentresult.dao.Subject;
import com.management.student.studentresult.repository.SubjectRepository;
import com.management.student.studentresult.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/addSubject")
    public Subject addSubject(@RequestBody Subject subject){
        return subjectService.saveSubject(subject);
    }
}
