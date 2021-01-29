package com.example.shiro.service;

import com.example.shiro.dao.RoleDao;
import com.example.shiro.dao.UserDao;
import com.example.shiro.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

//    @Autowired
//    PermissionDao permissionDao;

    /**
     * SpringSecurity所需的：获取、组装用户信息（含授权信息）的UserDetails对象
     * @param
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInfo = userDao.findUserByUserId(username);

        if(userInfo == null){
            throw new UsernameNotFoundException("用户不存在！");
        }

//        List<String> roleIds = roleDao.findByUserId(userInfo.getId());
//        List<String> permissions = permissionDao.findByRoleId(roleIds);
//
//        String[] permissionArray = new String[permissions.size()];
//        Arrays.stream(permissionArray).toArray();
//        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(userInfo.getUsername()).password(userInfo.getPassword()).authorities(permissionArray)
//                .build();
        return userInfo;


    }
}