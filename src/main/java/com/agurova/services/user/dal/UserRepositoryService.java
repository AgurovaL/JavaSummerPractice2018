package com.agurova.services.user.dal;

import com.agurova.models.User;

import java.util.List;

public interface UserRepositoryService {
    List<User> getAll();

    User findByID(Long id);

    void save(User user);

    void delete(Long id);
}
