package com.moroz.controllers;

import com.moroz.exceptions.UserNotFoundException;
import com.moroz.model.UserDTO;
import com.moroz.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO.getFullName(), userDTO.getEmailPhoneNumber());
    }

    @PostMapping(path = "/delete/{emailphonenumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUserById(@PathVariable(value = "emailphonenumber") String emailPhoneNumber) {
        userService.deleteUserByEmailPhoneNumber(emailPhoneNumber);
        return new ResponseEntity<String>(emailPhoneNumber, HttpStatus.OK);
    }
}
