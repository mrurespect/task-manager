package fsts.mrurespect.backendspring.service;


import fsts.mrurespect.backendspring.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User registerUser(User user);
    User login(User user);
    List<User> getUsers();
    User findUserByEmail(String email);
}
