package com.management.student.studentresult.service;


import com.management.student.studentresult.dao.Subject;
import com.management.student.studentresult.repository.SubjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

public class SubjectServiceTest {

    @Mock
    private SubjectRepository repository;

    @InjectMocks
    private SubjectService subjectService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    public Subject getSubject(){
        return new Subject("Subject", "Subcode", 1, 2020, 100);
    }

    @Test
    public void saveSubjectTest(){
        Mockito.when(repository.save(any(Subject.class))).thenReturn(getSubject());
        Subject subject = this.subjectService.saveSubject(getSubject());
        Assertions.assertNotNull(subject);
    }

}
