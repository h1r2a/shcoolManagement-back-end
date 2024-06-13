package harena.dev.banking.service;


import harena.dev.banking.dto.requestDto.CourseRequestDto;
import harena.dev.banking.dto.responseDto.CourseResponseDto;
import harena.dev.banking.entity.Course;

import java.util.List;

public interface CourseService {
    CourseResponseDto createCourse(CourseRequestDto courseRequestDto);
    List<CourseResponseDto> getAllCourse();
    CourseResponseDto getCourseByID(Long id);

}
