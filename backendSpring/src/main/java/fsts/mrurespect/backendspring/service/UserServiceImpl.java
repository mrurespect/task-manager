package fsts.mrurespect.backendspring.service;

import fsts.mrurespect.backendspring.entity.User;
import fsts.mrurespect.backendspring.exception.UserException;
import fsts.mrurespect.backendspring.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        if (userRepository.existsUserByEmail(user.getEmail()))throw new UserException("user with email already exist", HttpStatus.CONFLICT);
        return userRepository.save(user);
    }

    @Override
    public User login(User user) {
        User dbUser =userRepository.getUserByEmailAndPassword(user.getEmail(),user.getPassword());
        if (dbUser ==null)throw new UserException("invalid credential",HttpStatus.UNAUTHORIZED);
        return dbUser;
    }
}
