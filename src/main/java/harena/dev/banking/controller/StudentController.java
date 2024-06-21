package harena.dev.banking.controller;


import harena.dev.banking.dto.requestDto.StudentRequestDto;
import harena.dev.banking.dto.responseDto.StudentResponseDto;
import harena.dev.banking.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping
    public ResponseEntity<Object> getAllStudent(){
        List<StudentResponseDto> studentResponseDtos = studentService.getAllStundets();
        return new ResponseEntity<>(studentResponseDtos,HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Object> getStudentById(@PathVariable Long studentId){
        StudentResponseDto studentResponseDto = studentService.getStudentById(studentId);
        return new ResponseEntity<>(studentResponseDto,HttpStatus.OK);
    }

    @GetMapping("/notInCourse/{courseId}")
    public ResponseEntity<Object> getStudentNotInCourse(@PathVariable Long courseId){
        List<StudentResponseDto> studentList = studentService.getStudentNotInCourse(courseId);
        return ResponseEntity.ok().body(studentList);
    }

}
