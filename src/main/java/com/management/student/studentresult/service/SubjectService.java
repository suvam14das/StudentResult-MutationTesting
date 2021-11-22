package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Subject;
import com.management.student.studentresult.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository repository;

    public Subject saveSubject(Subject subject){
        return repository.save(subject);
    }

}
