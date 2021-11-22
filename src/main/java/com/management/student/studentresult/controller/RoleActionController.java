package com.management.student.studentresult.controller;

import com.management.student.studentresult.dao.Action;
import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.service.ActionService;
import com.management.student.studentresult.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class RoleActionController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private ActionService actionService;

    @PostMapping("/addRole")
    public Role addRole(@RequestBody Role role){

        Role newrole = new Role(role.getName(), role.getCreatedBy(), role.getModifiedBy());
        return roleService.saveRole(newrole);
    }

    @GetMapping("/allRoles")
    public @ResponseBody ResponseEntity<?> allRoles(){
        List<String> roleNamesList= new ArrayList<>();
        try {
            List<Role> rolesList=roleService.getRoles();
            for(int i=0;i<rolesList.size();i++)
                roleNamesList.add(rolesList.get(i).getName());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("Error in fetching Roles!", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<List<String>>(roleNamesList,HttpStatus.OK);
    }

    @GetMapping("/actionsByRole/{role}")
    public ResponseEntity<?> actionsByRole(@PathVariable String role){
        try{
            List<Action> roleActions = actionService.getActionsByRole(role);
            if(roleActions == null)
                throw new Exception();
            return new ResponseEntity<List<Action>>(roleActions, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<String>("Invalid Role", HttpStatus.BAD_REQUEST);
        }
    }

}
