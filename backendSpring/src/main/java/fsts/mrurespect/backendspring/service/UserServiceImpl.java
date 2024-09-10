package fsts.mrurespect.backendspring.service;

import fsts.mrurespect.backendspring.entity.User;
import fsts.mrurespect.backendspring.exception.UserException;
import fsts.mrurespect.backendspring.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, EntityManager entityManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        String hashPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPwd);
        if (userRepository.existsUserByEmail(user.getEmail()))throw new UserException("user with email already exist", HttpStatus.CONFLICT);
        return userRepository.save(user);
    }

    @Override
    public User login(User user) {
        User dbUser =userRepository.getUserByEmailAndPassword(user.getEmail(),user.getPassword());
        if (dbUser ==null)throw new UserException("invalid credential",HttpStatus.UNAUTHORIZED);
        return dbUser;
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


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

}
