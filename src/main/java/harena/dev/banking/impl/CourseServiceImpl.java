package harena.dev.banking.impl;

import harena.dev.banking.dto.Mapper;
import harena.dev.banking.dto.requestDto.CourseRequestDto;
import harena.dev.banking.dto.responseDto.CourseResponseDto;
import harena.dev.banking.entity.Course;
import harena.dev.banking.entity.Teacher;
import harena.dev.banking.repository.CourseRepository;
import harena.dev.banking.repository.TeacherRepository;
import harena.dev.banking.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        Teacher teacher ;
        teacher = teacherRepository.findById(courseRequestDto.getTeacherId()).orElseThrow(()->new RuntimeException("Teacher not found"));
        Course course =  new Course(null, courseRequestDto.getName(), courseRequestDto.getDuration(), courseRequestDto.getDescription(),teacher,new ArrayList<>());
        Course savedCourse = courseRepository.save(course);
        return Mapper.courseToResponseDto(savedCourse);
    }


    @Override
    public CourseResponseDto getCourseByID(Long id) {
        Course course;
        course = courseRepository.findById(id).orElseThrow(()->new RuntimeException("Course not Found"));
        return Mapper.courseToResponseDto(course);
    }

    @Override
    public List<CourseResponseDto> getAllCourse() {
        List<CourseResponseDto> courses = new ArrayList<>();
        List<Course> courseList = courseRepository.findAll();
        return Mapper.courseToResponseDtos(courseList);
    }



}
