package com.example.shiro.service;

import com.example.shiro.dao.RoleDao;
import com.example.shiro.dao.UserDao;
import com.example.shiro.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
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
     *
     * @param
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInfo = userDao.findUserByUserId(username);

        if (userInfo == null) {
            log.error("用户{}不存在", username);
            throw new UsernameNotFoundException("用户不存在！");
        }

        return userInfo;


    }
}