package com.example.shiro.dao;

import com.example.shiro.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    @Query(value = "SELECT * from t_user where id = ?1", nativeQuery = true)
    User findUserByUserId(String userId);

    @Query(value = "SELECT p.name FROM t_role_permissions as rp,t_permission as p, t_user_roles as ur,t_role as r\n" +
            "where ur.roles_id = r.id and r.id = rp.t_role_id  and rp.permissions_id = p.id\n" +
            "and ur.t_user_id = ?1", nativeQuery = true)
    List<String> findPermissionsByUserId(String userId);

    User findUserByUsername(String username);
}