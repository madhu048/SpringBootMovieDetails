package com.demo.demo.service;

import java.util.List;

import com.demo.demo.DTOclasses.UserRequestDTO;
import com.demo.demo.DTOclasses.UserResponseDTO;

public interface IuserService {
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    public UserResponseDTO getUser(int id);

    public List<UserResponseDTO> getAllUsers();

    public String deleteUser(int id);

    public UserResponseDTO updateUser(int id, UserRequestDTO userRequestDTO);
}
