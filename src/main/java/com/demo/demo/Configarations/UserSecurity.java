package com.demo.demo.Configarations;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.demo.demo.Repogitory.userRepo;

@Component
public class UserSecurity {

    private final userRepo userRepo;

    public UserSecurity(userRepo userRepo) {
        this.userRepo = userRepo;
    }

    // This will check the logged in user with db details to ensure the user owner
    // of the page/record.
    // Authentication represents the logged in user(pricipal,rols,etc) details.
    public boolean isOwner(int id, Authentication authentication) {
        String email = authentication.getName();
        return userRepo.findById(id).map(user -> user.getEmail().equals(email)).orElse(false);
    }
}
