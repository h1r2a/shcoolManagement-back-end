package harena.dev.banking.impl;

import harena.dev.banking.dto.Mapper;
import harena.dev.banking.dto.requestDto.StudentRequestDto;
import harena.dev.banking.dto.responseDto.StudentResponseDto;
import harena.dev.banking.entity.Course;
import harena.dev.banking.entity.Student;
import harena.dev.banking.entity.User;
import harena.dev.banking.repository.CourseRepository;
import harena.dev.banking.repository.StudentRepository;
import harena.dev.banking.repository.UserRepository;
import harena.dev.banking.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        User user = userRepository.findById(studentRequestDto.getUserId()).orElseThrow(
                ()->new RuntimeException("User not found for id "+ studentRequestDto.getUserId()));
        Student student= new Student(null, studentRequestDto.getFirstName(), studentRequestDto.getLastName(),
                studentRequestDto.getLevel(),user,new ArrayList<>());
        Student savedStudent = studentRepository.save(student);
        return Mapper.studentToResponseDto(savedStudent);
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()->new RuntimeException("No student found for id "+id));
        return Mapper.studentToResponseDto(student);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return Mapper.studentToResponseDtos(students);
    }

    @Override
    public List<StudentResponseDto> getStudentNotInCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("Course With Id "+courseId+" Not found"));
        List<Student> studentList = studentRepository.findStudentsNotInCourse(course);

        return Mapper.studentToResponseDtos(studentList);
    }

    @Override
    public StudentResponseDto subscribeStudentToCourses(Map<String, Object> studentAndCourseIds) {
        Long studentId =((Number) studentAndCourseIds.get("studentId")).longValue();
        Student student = studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student not found for id "+studentId));
        List<Long> courseIds = ((List<?>) studentAndCourseIds.get("courseIds"))
                .stream()
                .map(number ->((Number) number).longValue())
                .collect(Collectors.toList());
        List<Course> courseList = new ArrayList<>();
        for (Long courseId : courseIds){
            Course course =  courseRepository.findById(courseId)
                    .orElseThrow(()->new RuntimeException("course not found for id"+courseId));
            courseList.add(course);
        }

        if(!courseList.isEmpty()){
            for (Course course:courseList){
                if(!student.getCourseList().contains(course)){
                    student.getCourseList().add(course);
                    course.getStudentList().add(student);
                }
            }
        }

        Student savedStudent = studentRepository.save(student);

        
        return Mapper.studentToResponseDto(savedStudent);
    }


}
