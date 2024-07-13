package harena.dev.banking.service;

import harena.dev.banking.dto.requestDto.StudentRequestDto;
import harena.dev.banking.dto.responseDto.StudentResponseDto;

import java.util.List;
import java.util.Map;

public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto studentRequestDto);

    StudentResponseDto getStudentById(Long id);

    List<StudentResponseDto> getAllStudents();

    List<StudentResponseDto> getStudentNotInCourse(Long courseId);

    StudentResponseDto subscribeStudentToCourses(Map<String,Object> studentAndCourseIds);

}
