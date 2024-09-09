package fsts.mrurespect.backendspring.service;


import fsts.mrurespect.backendspring.entity.User;

public interface UserService {
    User registerUser(User user);
    User login(User user);
}
