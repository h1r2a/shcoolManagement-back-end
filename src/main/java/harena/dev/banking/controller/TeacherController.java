package harena.dev.banking.controller;

import harena.dev.banking.dto.requestDto.TeacherRequestDto;
import harena.dev.banking.dto.responseDto.TeacherResponseDto;
import harena.dev.banking.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @PostMapping
    public ResponseEntity<Object> createTeacher(@RequestBody TeacherRequestDto teacherRequestDto){
        TeacherResponseDto teacherResponseDto = teacherService.createTeacher(teacherRequestDto);
        return new ResponseEntity<>(teacherResponseDto, HttpStatus.CREATED);
    }
}
