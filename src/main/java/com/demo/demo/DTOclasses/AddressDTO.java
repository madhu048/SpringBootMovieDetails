package com.demo.demo.DTOclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Integer addressId;
    private String street;
    private long pincode;
    private String city;
    private String state;

}
