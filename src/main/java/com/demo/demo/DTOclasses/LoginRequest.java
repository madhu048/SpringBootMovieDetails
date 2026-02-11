package com.demo.demo.DTOclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest { // This class is used to receive the login request from the client and to send
                            // the response back to the client after successful login
    private String email;
    private String password;
}
