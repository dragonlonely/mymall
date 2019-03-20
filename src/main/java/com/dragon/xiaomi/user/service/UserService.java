package com.dragon.xiaomi.user.service;


import com.dragon.xiaomi.user.pojo.User;

import java.util.List;

/**
 * Created by jackiechan on 10/16/18/6:38 PM
 *
 * @Author jackiechan
 */
public interface UserService {
    /**
     * 添加用户
     * @param user
     * @throws Exception
     */
    void addUser(User user) throws Exception;

    int checkUserName(String username);

    User findUserByUsernameAndPassword(String username, String password);

    void updatePassword(User user2);

    User adminLogin(String username, String password);

    List<User> findAllUser(String username,String gender);


    List<User> getUserList();


    void remove(int id);

    String findUserNameById(int uid);

    int getIdByUsername(String username);

    List<User> getInvalidUserList();

    String getPasswordSaltByUsername(String username);
}
