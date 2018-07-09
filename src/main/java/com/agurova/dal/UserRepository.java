package com.agurova.dal;

import com.agurova.models.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User findByID(Long id);

    void save(User user);

    void delete(Long id);
}
