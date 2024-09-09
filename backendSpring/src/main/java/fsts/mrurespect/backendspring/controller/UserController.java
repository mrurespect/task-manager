package fsts.mrurespect.backendspring.controller;

import fsts.mrurespect.backendspring.entity.User;
import fsts.mrurespect.backendspring.exception.UserException;
import fsts.mrurespect.backendspring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody  User user) throws UserException {
        userService.registerUser(user);
        return new ResponseEntity<>("account created successfully",HttpStatus.CREATED);
    }
   @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody  User user) throws UserException {
        userService.login(user);
        return new ResponseEntity<>("logged in successfully",HttpStatus.OK);
    }
}
