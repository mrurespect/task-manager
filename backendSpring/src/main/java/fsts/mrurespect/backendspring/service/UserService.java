package fsts.mrurespect.backendspring.service;


import fsts.mrurespect.backendspring.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void registerUser(User user);
    List<User> getUsers();
    User findUserByEmail(String email);
}
