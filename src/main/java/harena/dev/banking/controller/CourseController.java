package harena.dev.banking.controller;

import harena.dev.banking.config.CustomRole;
import harena.dev.banking.dto.requestDto.CourseRequestDto;
import harena.dev.banking.dto.requestDto.UserRequestDto;
import harena.dev.banking.dto.responseDto.CourseResponseDto;
import harena.dev.banking.dto.responseDto.UserResponseDto;
import harena.dev.banking.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    @CustomRole({"admin"})
    public ResponseEntity<Object> createUser(@RequestBody CourseRequestDto courseRequestDto) {
        CourseResponseDto course = courseService.createCourse(courseRequestDto);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }


    @GetMapping
    public  ResponseEntity<Object> getAllCourse(){
        List<CourseResponseDto> courses = courseService.getAllCourse();
        return ResponseEntity.ok(courses);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id){
        CourseResponseDto course = courseService.getCourseByID(id);
        return ResponseEntity.ok(course);
    }



}
