package harena.dev.banking.service;

import harena.dev.banking.dto.requestDto.TeacherRequestDto;
import harena.dev.banking.dto.responseDto.TeacherResponseDto;

public interface TeacherService {
    TeacherResponseDto createTeacher(TeacherRequestDto teacherRequestDto);
}
