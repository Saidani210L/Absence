package com.example.absence.Repository;

import com.example.absence.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("select u FROM Role u WHERE u.userId =?1 AND u.role =?2")
    public Role getRoleByUserIdAndRole(int userId, String role);
}