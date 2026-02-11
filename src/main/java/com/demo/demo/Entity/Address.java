package com.demo.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address_table")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;
    @Column(name = "street")
    private String street;
    @Column(name = "pincode")
    private long pincode;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
