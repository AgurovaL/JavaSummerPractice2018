package com.agurova.services.user.impl;

import com.agurova.dal.UserRepository;
import com.agurova.models.User;
import com.agurova.services.user.UserRepositoryService;

import java.util.List;

public class UserRepositoryServiceImpl implements UserRepositoryService {
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User findByID(Long id) {
        return userRepository.findByID(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }
}
