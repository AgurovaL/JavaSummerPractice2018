package com.agurova.services.user.dal.impl;

import com.agurova.dal.impl.UserRepositoryImpl;
import com.agurova.models.User;
import com.agurova.services.user.dal.UserRepositoryService;
import lombok.Data;

import java.util.List;

@Data
public class UserRepositoryServiceImpl implements UserRepositoryService {
    private UserRepositoryImpl userRepository;

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
