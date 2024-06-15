package harena.dev.banking.impl;

import harena.dev.banking.dto.Mapper;
import harena.dev.banking.dto.requestDto.StudentRequestDto;
import harena.dev.banking.dto.responseDto.StudentResponseDto;
import harena.dev.banking.entity.Student;
import harena.dev.banking.entity.User;
import harena.dev.banking.repository.StudentRepository;
import harena.dev.banking.repository.UserRepository;
import harena.dev.banking.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

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
    public List<StudentResponseDto> getAllStundets() {
        List<Student> students = studentRepository.findAll();
        return Mapper.studentToResponseDtos(students);
    }


}
