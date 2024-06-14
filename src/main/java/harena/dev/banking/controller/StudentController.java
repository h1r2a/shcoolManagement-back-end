package harena.dev.banking.controller;


import harena.dev.banking.dto.requestDto.StudentRequestDto;
import harena.dev.banking.dto.responseDto.StudentResponseDto;
import harena.dev.banking.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/etudiant")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping
    public ResponseEntity<Object> createStudent(@RequestBody StudentRequestDto studentRequestDto){
        StudentResponseDto studentResponseDto = studentService.createStudent(studentRequestDto);
        return new ResponseEntity<>(studentResponseDto, HttpStatus.CREATED);
    }

}
