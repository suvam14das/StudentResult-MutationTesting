package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Action;
import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.repository.ActionRepository;
import com.management.student.studentresult.repository.RoleActionRepository;
import com.management.student.studentresult.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ActionServiceTest {

    @Mock
    private ActionRepository actionRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RoleActionRepository roleActionRepository;

    @InjectMocks
    private ActionService actionService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void addActionTest(){
        Action action = new Action();
        action.setName("SAMPLE_UPDATE");
        Mockito.when(actionRepository.save(any(Action.class))).thenReturn(action);
        Action savedAction = this.actionService.addAction(action);
        Assertions.assertEquals("SAMPLE_UPDATE", savedAction.getName());
    }

}

