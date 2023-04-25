package com.example.absence.Controller;

import com.example.absence.Model.Role;
import com.example.absence.Service.Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/add")
    public Role saveRole(@RequestBody Role role){
        return roleService.saveRole(role);
    }
    @GetMapping("/getAll")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }
    @PostMapping("/login/{userEmail}/{userPassword}")
    public List<Object> login(
            @PathVariable String userEmail,
            @PathVariable String userPassword
    ){
        return roleService.login(userEmail, userPassword);
    }

}
