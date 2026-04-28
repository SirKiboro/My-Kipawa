package com.my.kipawa.modules.user.service;

import com.my.kipawa.common.exception.ResourceNotFoundException;
import com.my.kipawa.modules.user.dto.UserResponseDTO;
import com.my.kipawa.modules.user.entity.User;
import com.my.kipawa.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

     // Retrieves a user by ID and converts it to a clean DTO.
     // Throws ResourceNotFoundException if the ID doesn't exist.

    public UserResponseDTO getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        return modelMapper.map(user, UserResponseDTO.class);
    }
}
