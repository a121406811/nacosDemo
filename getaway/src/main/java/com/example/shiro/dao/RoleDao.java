package com.example.shiro.dao;

import com.example.shiro.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, Long> {

    @Query(value = "SELECT t.name FROM t_user_roles as ur,t_role as t where ur.roles_id = t.id and ur.t_user_id = ?1", nativeQuery = true)
    List<String> findByUserId(Long userId);

    List<Role> findAll();
}
