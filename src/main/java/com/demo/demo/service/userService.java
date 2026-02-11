package com.demo.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.demo.DTOclasses.AddressDTO;
import com.demo.demo.DTOclasses.UserRequestDTO;
import com.demo.demo.DTOclasses.UserResponseDTO;
import com.demo.demo.Entity.Address;
import com.demo.demo.Entity.User;
import com.demo.demo.ExceptionHandler.NotFoundEx;
import com.demo.demo.ExceptionHandler.UserAlreadyExit;
import com.demo.demo.Repogitory.userRepo;

@Service
public class userService implements IuserService {

    @Autowired
    private userRepo userRep;

    @Autowired
    private PasswordEncoder pwEncoder;

    // User Creation
    public UserResponseDTO createUser(UserRequestDTO dto) {
        if (userRep.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExit("User already exists with email: " + dto.getEmail());
        }
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(pwEncoder.encode(dto.getPassword()));
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRole("ROLE_USER");

        if (dto.getAddresses() != null) {
            List<Address> addresses = new ArrayList<>(); // Create an empty list to hold the Address objects
            for (AddressDTO addressDTO : dto.getAddresses()) {
                Address address = new Address(); // create a new Address object for each addressDTO to set the values
                address.setStreet(addressDTO.getStreet());
                address.setCity(addressDTO.getCity());
                address.setPincode(addressDTO.getPincode());
                address.setState(addressDTO.getState());
                address.setUser(user);
                addresses.add(address); // Add the address to the empty addresses list
            }
            user.setAddresses(addresses);
        }
        User savedUser = userRep.save(user);

        UserResponseDTO urspDTO = new UserResponseDTO();
        urspDTO.setId(savedUser.getId());
        urspDTO.setFirstName(savedUser.getFirstName());
        urspDTO.setLastName(savedUser.getLastName());
        urspDTO.setEmail(savedUser.getEmail());
        urspDTO.setPhoneNumber(savedUser.getPhoneNumber());

        List<AddressDTO> aDtos = new ArrayList<>();
        if (savedUser.getAddresses() != null) {
            for (Address address : savedUser.getAddresses()) {
                AddressDTO aDto = new AddressDTO();
                aDto.setAddressId(address.getAddressId());
                aDto.setStreet(address.getStreet());
                aDto.setCity(address.getCity());
                aDto.setPincode(address.getPincode());
                aDto.setState(address.getState());
                aDtos.add(aDto);
            }
        }
        urspDTO.setAddresses(aDtos);
        return urspDTO;
    }

    // Fetching single User Details
    public UserResponseDTO getUser(int id) {
        Optional<User> userData = userRep.findById(id);
        if (userData.isPresent()) {
            User dbUser = userData.get();
            UserResponseDTO userDTO = new UserResponseDTO();
            userDTO.setId(dbUser.getId());
            userDTO.setFirstName(dbUser.getFirstName());
            userDTO.setLastName(dbUser.getLastName());
            userDTO.setEmail(dbUser.getEmail());
            userDTO.setPhoneNumber(dbUser.getPhoneNumber());

            List<AddressDTO> addressDTOs = new ArrayList<>();
            if (dbUser.getAddresses() != null) {
                for (Address address : dbUser.getAddresses()) {
                    AddressDTO addressDTO = new AddressDTO();
                    addressDTO.setAddressId(address.getAddressId());
                    addressDTO.setStreet(address.getStreet());
                    addressDTO.setCity(address.getCity());
                    addressDTO.setPincode(address.getPincode());
                    addressDTO.setState(address.getState());
                    addressDTOs.add(addressDTO);
                }
            }

            userDTO.setAddresses(addressDTOs);
            return userDTO;
        } else {
            throw new NotFoundEx("User Not Found With Id: " + id);
        }
    }

    // Fetching all users Details
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRep.findAll();
        List<UserResponseDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId((user.getId()));
            userResponseDTO.setFirstName(user.getFirstName());
            userResponseDTO.setLastName(user.getLastName());
            userResponseDTO.setEmail(user.getEmail());
            userResponseDTO.setPhoneNumber(user.getPhoneNumber());

            List<AddressDTO> addressDTOs = new ArrayList<>();
            if (user.getAddresses() != null) {
                for (Address address : user.getAddresses()) {
                    AddressDTO addressDTO = new AddressDTO();
                    addressDTO.setAddressId(address.getAddressId());
                    addressDTO.setStreet(address.getStreet());
                    addressDTO.setCity(address.getCity());
                    addressDTO.setPincode(address.getPincode());
                    addressDTO.setState(address.getState());
                    addressDTOs.add(addressDTO);
                }
            }

            userResponseDTO.setAddresses(addressDTOs);
            userDTOs.add(userResponseDTO);
        }
        return userDTOs;
    }

    // Deleting a user
    public String deleteUser(int id) {
        Optional<User> userData = userRep.findById(id);
        if (userData.isPresent()) {
            userRep.deleteById(id);
            return "User deleted successfully";
        } else {
            throw new NotFoundEx("User Not Found With Id: " + id);
        }

    }

    // Updating full details of a user
    public UserResponseDTO updateUser(int id, UserRequestDTO urqDTO) {
        Optional<User> existingUser = userRep.findById(id);
        if (existingUser.isPresent()) {
            // add the new value to dbUser directly and save it, no need to create a new
            // user object
            User dbUser = existingUser.get();
            dbUser.setFirstName(urqDTO.getFirstName());
            dbUser.setLastName(urqDTO.getLastName());
            dbUser.setEmail(urqDTO.getEmail());
            dbUser.setPassword(pwEncoder.encode(urqDTO.getPassword()));
            dbUser.setPhoneNumber(urqDTO.getPhoneNumber());

            // List<Address> addresses = new ArrayList<>();
            List<Address> existingAddresses = dbUser.getAddresses();
            // clear the existing addresses list to remove all the existing addresses of the
            // user before adding the new addresses from the request DTO.
            // This is necessary because we are updating the user's details, and if we don't
            // clear the existing addresses,
            // we might end up with duplicate addresses in the database when we add the new
            // addresses from the request DTO.
            existingAddresses.clear();
            if (urqDTO.getAddresses() != null) {
                for (AddressDTO addressDTO : urqDTO.getAddresses()) {
                    Address address = new Address();
                    address.setStreet(addressDTO.getStreet());
                    address.setCity(addressDTO.getCity());
                    address.setPincode(addressDTO.getPincode());
                    address.setState(addressDTO.getState());
                    address.setUser(dbUser);
                    existingAddresses.add(address);
                }

            }
            dbUser.setAddresses(existingAddresses);
            User updatedUser = userRep.save(dbUser);

            UserResponseDTO urpDTO = new UserResponseDTO();
            urpDTO.setId(updatedUser.getId());
            urpDTO.setFirstName(updatedUser.getFirstName());
            urpDTO.setLastName(updatedUser.getLastName());
            urpDTO.setEmail(updatedUser.getEmail());
            urpDTO.setPhoneNumber(updatedUser.getPhoneNumber());

            List<AddressDTO> aDTOs = new ArrayList<>();
            if (updatedUser.getAddresses() != null) {
                for (Address address : updatedUser.getAddresses()) {
                    AddressDTO aDTO = new AddressDTO();
                    aDTO.setAddressId(address.getAddressId());
                    aDTO.setStreet(address.getStreet());
                    aDTO.setCity(address.getCity());
                    aDTO.setPincode(address.getPincode());
                    aDTO.setState(address.getState());
                    // while adding address values, no need to set user value in addressDTO as it is
                    // not required in response, we only need to set user value in address entity.
                    aDTOs.add(aDTO);
                }
            }

            urpDTO.setAddresses(aDTOs);
            return urpDTO;
        } else {
            throw new NotFoundEx("User Not Found With Id: " + id);
        }

    }

}
