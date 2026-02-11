package com.demo.demo.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.demo.Entity.User;
import com.demo.demo.Repogitory.userRepo;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

    private final userRepo userRepo;

    public CustomeUserDetailsService(userRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // Convert User entity to Spring Security's UserDetails object
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole()))); // SimpleGrantedAuthority wraps rols in a format
                                                                      // Spring Security understands.
                                                                      // Spring Security expects a collection of
                                                                      // authorities. So even one role must be wrapped
                                                                      // in a list
    }

}
