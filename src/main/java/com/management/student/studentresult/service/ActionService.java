package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Action;
import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.repository.ActionRepository;
import com.management.student.studentresult.repository.RoleActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private RoleActionRepository roleActionRepository;

    public Action addAction(Action action){
        return actionRepository.save(action);
    }

    public List<Action> getActionsByRole(String roleName){
        RoleService roleService = new RoleService();
        Role role = roleService.getRoleByName(roleName);
        if(role == null)
            return null;
        return roleActionRepository.findByRoleAndStatus(role, "ACTIVE");
    }

}
