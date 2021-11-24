package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

public class RoleServiceTest {

    @Mock
    private RoleRepository repository;

    @InjectMocks
    private RoleService roleService;

    public Role getRole(){
        return new Role("sampleRole", null, null);
    }

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveRoleTest(){
        Mockito.when(repository.save(any(Role.class))).thenReturn(getRole());
        Role role = this.roleService.saveRole(getRole());
        Assertions.assertNotNull(role);
    }

    @Test
    public void getAllRolesTest(){
        List<Role> roleGt = new ArrayList<>();
        roleGt.add(getRole());roleGt.add(getRole());roleGt.add(getRole());
        Mockito.when(repository.findAll()).thenReturn(roleGt);
        List<Role> roleList = this.roleService.getRoles();
        Assertions.assertNotNull(roleList);
        Assertions.assertEquals(roleGt.size(), roleList.size());
    }

    @Test
    public void getRoleByIdTest(){
        Mockito.when(repository.findById(anyInt())).thenReturn(Optional.of(getRole()));
        Assertions.assertNotNull(this.roleService.getRoleById(1));
    }

    @Test
    public void getRoleByNameTest(){
        Mockito.when(repository.findByName(anyString())).thenReturn(Optional.of(getRole()));
        Assertions.assertNotNull(this.roleService.getRoleByName("student"));
    }

}
