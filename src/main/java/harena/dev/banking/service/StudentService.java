package harena.dev.banking.service;

import harena.dev.banking.dto.requestDto.StudentRequestDto;
import harena.dev.banking.dto.responseDto.StudentResponseDto;

public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto studentRequestDto);
}
