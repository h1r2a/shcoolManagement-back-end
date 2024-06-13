package harena.dev.banking.service;

import harena.dev.banking.dto.requestDto.UserRequestDto;
import harena.dev.banking.dto.responseDto.UserResponseDto;
import harena.dev.banking.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
     UserResponseDto createUser(UserRequestDto userRequestDto);
     UserResponseDto getUserById(Long id);

    List<UserResponseDto> getAllUser();
}
