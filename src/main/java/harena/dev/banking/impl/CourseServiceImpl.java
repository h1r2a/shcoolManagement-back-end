package harena.dev.banking.impl;

import harena.dev.banking.dto.Mapper;
import harena.dev.banking.dto.requestDto.CourseRequestDto;
import harena.dev.banking.dto.responseDto.CourseResponseDto;
import harena.dev.banking.entity.Course;
import harena.dev.banking.entity.Student;
import harena.dev.banking.entity.Teacher;
import harena.dev.banking.entity.User;
import harena.dev.banking.repository.CourseRepository;
import harena.dev.banking.repository.StudentRepository;
import harena.dev.banking.repository.TeacherRepository;
import harena.dev.banking.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

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

/*    @Override
    public CourseResponseDto addStudent(Map<String, Object> courseAndStudentIds) {
        Long courseId = ((Number)courseAndStudentIds.get("courseId")).longValue();
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found for id " + courseId));

        List<Long> studentIds = ((List<?>) courseAndStudentIds.get("studentIds"))
                .stream()
                .map(number-> ((Number) number).longValue()).collect(Collectors.toList());
        List<Student> students = new ArrayList<>();
        if(course!=null){
            for (Long id:studentIds){
                students.add(studentRepository.findById(id)
                        .orElseThrow(()->new  RuntimeException("student not found for id "+id)));
            }
        }
        if (course!= null && !students.isEmpty()){
            List<Student> currentStudentList = course.getStudentList();
            currentStudentList.addAll(students);
            course.setStudentList(currentStudentList);
        }
        Course courseSaved =  courseRepository.save(course);


        return Mapper.courseToResponseDto(courseSaved);
    }*/

    @Override
    public CourseResponseDto addStudent(Map<String, Object> courseAndStudentIds) {
        Long courseId = ((Number) courseAndStudentIds.get("courseId")).longValue();
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found for id " + courseId));

        List<Long> studentIds = ((List<?>) courseAndStudentIds.get("studentIds"))
                .stream()
                .map(number -> ((Number) number).longValue())
                .collect(Collectors.toList());

        List<Student> students = new ArrayList<>();
        for (Long id : studentIds) {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found for id " + id));
            students.add(student);
        }

        if (!students.isEmpty()) {
            for (Student student : students) {
                if (!course.getStudentList().contains(student)) {
                    course.getStudentList().add(student);
                    student.getCourseList().add(course);
                }
            }
        }

        Course courseSaved = courseRepository.save(course);

        return Mapper.courseToResponseDto(courseSaved);
    }



}
