package fsts.mrurespect.backendspring.service;

import fsts.mrurespect.backendspring.entity.User;
import fsts.mrurespect.backendspring.exception.UserException;
import fsts.mrurespect.backendspring.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(User user) {
        String hashPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPwd);
        if (userRepository.existsUserByEmail(user.getEmail()))
            throw new UserException("user with email already exist", HttpStatus.CONFLICT);
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByEmail(String email) {
        // retrieve/read from database using email
        TypedQuery<User> theQuery = entityManager.createQuery("from User where email=:uEmail", User.class);
        theQuery.setParameter("uEmail", email);

        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }
}
