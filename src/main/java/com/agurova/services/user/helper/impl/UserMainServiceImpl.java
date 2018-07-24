package com.agurova.services.user.helper.impl;

import com.agurova.models.User;
import com.agurova.services.user.dal.impl.UserRepositoryServiceImpl;
import com.agurova.services.user.helper.UserMainService;
import lombok.Data;
import org.apache.log4j.Logger;

import java.util.List;

@Data
public class UserMainServiceImpl implements UserMainService {
    private static final Logger LOG = Logger.getLogger(UserMainServiceImpl.class);

    private UserRepositoryServiceImpl userRepositoryService;

    public List<User> getAllUsers() {
        return userRepositoryService.getAll();
    }

    public String authorize (String login, String password){
        List<User> users = getAllUsers();
        for (User user: users){
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())){
                return user.getRole();
            }
        }
        LOG.error("Log in error. No user with such login/password!");
        return null;
    }
}