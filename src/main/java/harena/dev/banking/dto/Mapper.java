package harena.dev.banking.dto;

import harena.dev.banking.dto.responseDto.CourseResponseDto;
import harena.dev.banking.dto.responseDto.StudentResponseDto;
import harena.dev.banking.dto.responseDto.TeacherResponseDto;
import harena.dev.banking.dto.responseDto.UserResponseDto;
import harena.dev.banking.entity.Course;
import harena.dev.banking.entity.Student;
import harena.dev.banking.entity.Teacher;
import harena.dev.banking.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static UserResponseDto userToResponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setRole(user.getRole().getName());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }


    public static List<UserResponseDto> usersToResponseDtos(List<User> users){
       List<UserResponseDto> userResponseDtos = new ArrayList<>();
       for(User user : users){
           userResponseDtos.add(userToResponseDto(user));
       }
       return userResponseDtos;
    }


    public static CourseResponseDto courseToResponseDto(Course course){
        CourseResponseDto courseResponseDto = new CourseResponseDto();
        courseResponseDto.setId(course.getId());
        courseResponseDto.setName(course.getName());
        courseResponseDto.setDescription(course.getDescription());
        courseResponseDto.setDuration(course.getDuration());
        courseResponseDto.setTeacherName(course.getTeacher().getFirstName()+" "+course.getTeacher().getLastName());
        courseResponseDto.setStudents(studentToResponseDtos(course.getStudentList()));
        return courseResponseDto;
    }


    public static List<CourseResponseDto> courseToResponseDtos(List<Course> courses){
        List<CourseResponseDto> courseResponseDtos = new ArrayList<>();
        for (Course course : courses){
            courseResponseDtos.add(courseToResponseDto(course));
        }
        return courseResponseDtos;
    }


    public static StudentResponseDto studentToResponseDto(Student student){
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setFirstName(student.getFirstName());
        studentResponseDto.setLastName(student.getLastName());
        studentResponseDto.setLevel(student.getLevel());
        studentResponseDto.setUserId(student.getUser().getId());
        studentResponseDto.setCourse(courseToResponseDtos(student.getCourseList()));
        return studentResponseDto;

    }


    public static List<StudentResponseDto> studentToResponseDtos(List<Student> students){
        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();
        for(Student student :students){
            studentResponseDtos.add(studentToResponseDto(student));
        }
        return studentResponseDtos;
    }

    public static TeacherResponseDto teacherToResponseDto(Teacher teacher){
        TeacherResponseDto teacherResponseDto = new TeacherResponseDto();
        teacherResponseDto.setId(teacher.getId());
        teacherResponseDto.setFirstName(teacher.getFirstName());
        teacherResponseDto.setLastname(teacher.getLastName());
        teacherResponseDto.setPhoneNumber(teacher.getPhoneNumber());
        teacherResponseDto.setUserId(teacher.getUser().getId());
        teacherResponseDto.setCourses(courseToResponseDtos(teacher.getCourseList()));
        return teacherResponseDto;
    }

    public static  List<TeacherResponseDto> teacherToResponseDtos(List<Teacher> teachers){
        List<TeacherResponseDto> teacherResponseDtos = new ArrayList<>();
        for (Teacher teacher : teachers){
            teacherResponseDtos.add(teacherToResponseDto(teacher));
        }
        return teacherResponseDtos;
    }
}
