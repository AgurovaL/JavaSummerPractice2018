package com.agurova.services.user.dal;

import com.agurova.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserRepositoryService extends UserDetailsService {
    List<User> getAll();

    User findByID(Long id);

    void save(User user);

    void delete(Long id);

    UserDetails loadUserByUsername(String username);
}
