package fsts.mrurespect.backendspring.controller;

import fsts.mrurespect.backendspring.entity.JwtAuthResponse;
import fsts.mrurespect.backendspring.entity.User;
import fsts.mrurespect.backendspring.exception.UserException;
import fsts.mrurespect.backendspring.service.AuthService;
import fsts.mrurespect.backendspring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody  User user) throws UserException {
        userService.registerUser(user);
        return new ResponseEntity<>("account created successfully",HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers() throws UserException {
        return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
    }

    @GetMapping("/user-info")
    public User getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails);
        return userService.findUserByEmail(userDetails.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody User user){
        JwtAuthResponse jwtAuthResponse = null;
        try {
            String token = authService.login(user);
            jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setAccessToken(token);
        }catch (Exception e){
            throw new UserException("invalid credentials",HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
