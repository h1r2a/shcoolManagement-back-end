package harena.dev.banking.controller;

import harena.dev.banking.dto.requestDto.UserRequestDto;
import harena.dev.banking.dto.responseDto.UserResponseDto;
import harena.dev.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userService.createUser(userRequestDto);
        return  ResponseEntity.ok(userResponseDto);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticateUser(@RequestBody UserRequestDto userRequestDto){
        String token = userService.authenticate(userRequestDto);
        return ResponseEntity.ok(token);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id){
        UserResponseDto userResponseDto = userService.getUserById(id);
        return ResponseEntity.ok(userResponseDto);
    }
    @GetMapping()
    public ResponseEntity<Object> getAllUser(){
        List<UserResponseDto> userResponseDtos = userService.getAllUser();
        return ResponseEntity.ok(userResponseDtos);
    }


}
