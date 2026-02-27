package com.demo.demo.Utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.demo.Entity.Address;
import com.demo.demo.Entity.User;
import com.demo.demo.Repogitory.userRepo;

@Component
public class AdminUserCreation implements CommandLineRunner {
    private final userRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public AdminUserCreation(userRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String adminEmail = "admin123@gmail.com";
        if (userRepo.findByEmail(adminEmail).isEmpty()) {
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("admin@123"));
            admin.setPhoneNumber("1234567890");
            admin.setRole("ROLE_ADMIN");

            List<Address> addresses = new ArrayList<>();
            Address address = new Address();
            address.setStreet("Admin Street");
            address.setCity("Admin City");
            address.setState("Admin State");
            address.setPincode(000000);
            address.setUser(admin);
            addresses.add(address);

            admin.setAddresses(addresses);

            userRepo.save(admin);
        }
    }

}
