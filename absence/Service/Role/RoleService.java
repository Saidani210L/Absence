package com.example.absence.Service.Role;

import com.example.absence.Model.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleService {
    public Role saveRole(Role role);
    public List<Role> getRoles();
    public List<Object> login(String userEmail, String userPassword);
    public void motDePasseOublier(String email);
}
