package com.agurova.services.user.helper;

import com.agurova.models.User;

import java.util.List;

public interface UserMainService {
    List<User> getAllUsers();

    void save(User user);

    String authorize (String login, String password);
}
