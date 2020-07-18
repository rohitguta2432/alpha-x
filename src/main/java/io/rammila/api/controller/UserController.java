package io.rammila.api.controller;

import io.rammila.api.model.User;
import io.rammila.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public ResponseEntity<?> save(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }
}
