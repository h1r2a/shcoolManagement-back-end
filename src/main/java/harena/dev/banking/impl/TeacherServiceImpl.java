package harena.dev.banking.impl;

import harena.dev.banking.dto.Mapper;
import harena.dev.banking.dto.requestDto.TeacherRequestDto;
import harena.dev.banking.dto.responseDto.TeacherResponseDto;
import harena.dev.banking.entity.Teacher;
import harena.dev.banking.entity.User;
import harena.dev.banking.repository.TeacherRepository;
import harena.dev.banking.repository.UserRepository;
import harena.dev.banking.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public TeacherResponseDto createTeacher(TeacherRequestDto teacherRequestDto) {
        User user = userRepository.findById(teacherRequestDto.getUserId()).orElseThrow(
                ()->new RuntimeException("No user Found With userId: ->"+teacherRequestDto.getUserId()));
        Teacher teacher = new Teacher(null, teacherRequestDto.getFirstName(),
                teacherRequestDto.getLastName(),
                teacherRequestDto.getPhoneNumber(),
                user,new ArrayList<>());
        Teacher savedTeacher = teacherRepository.save(teacher);
        return Mapper.teacherToResponseDto(teacher);
    }
}
