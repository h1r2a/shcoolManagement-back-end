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
import java.util.Map;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    @CustomRole({"ROLE_ADMIN"})
    public ResponseEntity<Object> createCourse(@RequestBody CourseRequestDto courseRequestDto) {
        CourseResponseDto course = courseService.createCourse(courseRequestDto);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }


    @PostMapping("/addStudent")
    public ResponseEntity<Object> addStudents(@RequestBody Map<String, Object> courseIdAndStudentIds) {
        CourseResponseDto courseResponseDto = courseService.addStudent(courseIdAndStudentIds);
        return ResponseEntity.ok(courseResponseDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllCourse() {
        List<CourseResponseDto> courses = courseService.getAllCourse();
        return ResponseEntity.ok(courses);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> geCourseById(@PathVariable Long id) {
        CourseResponseDto course = courseService.getCourseByID(id);
        return ResponseEntity.ok(course);
    }
    @PostMapping("/test")
    public ResponseEntity<Object> testMap(@RequestBody Map<String,Object> body){
        Long id =( (Number) body.get("courseId")).longValue();
        //List<Long> idList = (List<Long>) body.get("studentIds");
        List<Long> idList = ((List<?>) body.get("studentIds")).stream()
                .map(number -> ((Number) number).longValue())
                .collect(Collectors.toList());

        System.out.println("voici l'Id "+id );
        System.out.println("voici les Id "+idList);
        return ResponseEntity.ok(idList);
    }



}
