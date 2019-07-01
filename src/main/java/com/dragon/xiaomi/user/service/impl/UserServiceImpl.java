package com.dragon.xiaomi.user.service.impl;


import com.dragon.xiaomi.mapper.UserMapper;
import com.dragon.xiaomi.user.pojo.User;
import com.dragon.xiaomi.user.service.UserService;
import com.dragon.xiaomi.utils.ActiveUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * Created by jackiechan on 10/16/18/6:39 PM
 *
 * @Author jackiechan
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    public void addUser(User user) throws Exception {
        //校验数据的合法性
        //执行数据库操作,dao

        //user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setFlag(1);
        user.setRole(1);
        user.setCode(ActiveUtils.createActiveCode());
        userMapper.addUser(user);
    }


    public int checkUserName(String username) {
        return userMapper.checkUserName(username);
    }


    public User findUserByUsernameAndPassword(String username, String password) {
        return userMapper.findUserByUsernameAndPassword(username, password);
    }


    public void updatePassword(User user2) {
        user2.setPassword(DigestUtils.md5DigestAsHex(user2.getPassword().getBytes()));
        user2.setCode(ActiveUtils.createActiveCode());
        userMapper.modifyPwd(user2);
    }


    public User adminLogin(String username, String password) {
        User user = userMapper.findUserByUsernameAndPassword(username, password);
        if (user != null) {
            if (user.getRole()!=0) {
                throw new RuntimeException("没有权限");
            }
        }else{
            throw new RuntimeException("用户名或密码错误");
        }
        return user;
    }


    public List<User> findAllUser(String username,String gender) {
        List<User> allUser = userMapper.findAllUser(username,gender);
        return allUser;
    }


    public List<User> getUserList() {
        List<User> userList = userMapper.getUserList();
        return userList;
    }


    public void remove(int userid) {
        userMapper.delete(userid);
    }


    public String findUserNameById(int uid) {
        return userMapper.findUserNameById(uid);
    }


    public int getIdByUsername(String username) {
        return userMapper.getIdByUsername(username);
    }


    public List<User> getInvalidUserList() {
        List<User> userList = userMapper.getInvalidUserList();
        return userList;
    }


    public String getPasswordSaltByUsername(String username) {
        return userMapper.getPasswordSaltByUsername(username);
    }
}
