package harena.dev.banking.impl;


import harena.dev.banking.dto.Mapper;
import harena.dev.banking.dto.requestDto.UserRequestDto;
import harena.dev.banking.dto.responseDto.UserResponseDto;
import harena.dev.banking.entity.Role;
import harena.dev.banking.entity.User;
import harena.dev.banking.repository.RoleRepository;
import harena.dev.banking.repository.UserRepository;
import harena.dev.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        Role role ;
        role = roleRepository.findByName(userRequestDto.getRole()).orElseThrow(()->new RuntimeException("Role not Found"));
        User user = new User(null, userRequestDto.getEmail(), userRequestDto.getPassword(), role);
        User savedUser = userRepository.save(user);
        UserResponseDto userResponseDto = new UserResponseDto();
        return Mapper.userToResponseDto(savedUser);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException(""));
        return Mapper.userToResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        userResponseDtoList = Mapper.usersToResponseDtos(userRepository.findAll());
        return userResponseDtoList;
    }
}
