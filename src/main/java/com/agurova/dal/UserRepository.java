package com.agurova.dal;

import com.agurova.models.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User findByID(Long id);

    User findByName(String name);

    Long addUser(User user);
}
