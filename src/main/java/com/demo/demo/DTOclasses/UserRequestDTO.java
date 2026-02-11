package com.demo.demo.DTOclasses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private List<AddressDTO> addresses;
}
