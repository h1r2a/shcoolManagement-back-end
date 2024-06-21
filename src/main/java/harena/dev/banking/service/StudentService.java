package harena.dev.banking.service;

import harena.dev.banking.dto.requestDto.StudentRequestDto;
import harena.dev.banking.dto.responseDto.StudentResponseDto;
import harena.dev.banking.entity.Course;

import java.util.List;

public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto studentRequestDto);

    StudentResponseDto getStudentById(Long id);

    List<StudentResponseDto> getAllStundets();

    List<StudentResponseDto> getStudentNotInCourse(Long courseId);
}
