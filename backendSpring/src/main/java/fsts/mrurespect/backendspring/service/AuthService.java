package fsts.mrurespect.backendspring.service;

import fsts.mrurespect.backendspring.entity.User;

public interface AuthService {
    String login(User user);
}
