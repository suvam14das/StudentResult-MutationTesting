package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public Role saveRole(Role role){
        return repository.save(role);
    }

    public List<Role> getRoles(){
        return repository.findAll();
    }

    public Role getRoleById(int id){
        return repository.findById(id).orElse(null);
    }
    
    public Role getRoleByName(String name){
        return repository.findByName(name).orElse(null);
    }
}
