package com.agurova.dal;

import com.agurova.models.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User findByID(Long id);

    void addUser(User user);

    void deleteUser(Long id);
}
