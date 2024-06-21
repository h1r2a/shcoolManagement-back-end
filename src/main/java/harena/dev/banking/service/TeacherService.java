package harena.dev.banking.service;

import harena.dev.banking.dto.requestDto.TeacherRequestDto;
import harena.dev.banking.dto.responseDto.TeacherResponseDto;

import java.util.List;

public interface TeacherService {
    TeacherResponseDto createTeacher(TeacherRequestDto teacherRequestDto);

    List<TeacherResponseDto> getAllTeacher();
}
