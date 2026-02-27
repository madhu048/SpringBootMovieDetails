package com.demo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.DTOclasses.UserRequestDTO;
import com.demo.demo.DTOclasses.UserResponseDTO;
import com.demo.demo.service.userService;

@RestController
@RequestMapping("/api")
public class usercontroller {

    @Autowired
    private userService userServiceo;

    @PostMapping("/saveUser")
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO urqDTO) {
        UserResponseDTO saved = userServiceo.createUser(urqDTO);
        ResponseEntity<UserResponseDTO> res = new ResponseEntity<>(saved, HttpStatus.CREATED);
        return res;
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isOwner(#id, authentication)")
    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable int id) {
        UserResponseDTO user = userServiceo.getUser(id);
        ResponseEntity<UserResponseDTO> res = new ResponseEntity<>(user, HttpStatus.OK);
        return res;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> allUsers = userServiceo.getAllUsers();
        ResponseEntity<List<UserResponseDTO>> res = new ResponseEntity<>(allUsers, HttpStatus.OK);
        return res;
    }

    // if the logged‑in user has role ROLE_ADMIN, it will allow, if not admin, it
    // will call UserSecurity.isOwner(...) method.
    // #id is the method parameter (the user id in the URL). authentication is the
    // current logged‑in user info. authentication is a built‑in variable that
    // Spring Security injects from the current SecurityContext.So it already
    // contains the logged‑in user for the current request (name, roles, etc.).

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isOwner(#id, authentication)")
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUserDetails(@PathVariable int id, @RequestBody UserRequestDTO urqDTO) {

        UserResponseDTO user = userServiceo.updateUser(id, urqDTO);
        ResponseEntity<UserResponseDTO> res = new ResponseEntity<>(user, HttpStatus.OK);
        return res;
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isOwner(#id, authentication)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        String result = userServiceo.deleteUser(id);
        ResponseEntity<String> res = new ResponseEntity<>(result, HttpStatus.OK);
        return res;
    }

}
