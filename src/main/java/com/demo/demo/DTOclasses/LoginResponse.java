package com.demo.demo.DTOclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse { // This class is used to send the response back to the client after sucessful
                             // login
    private String token;
    private String role;
    private Integer userId;
}
