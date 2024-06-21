package harena.dev.banking.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import harena.dev.banking.entity.Course;
import harena.dev.banking.entity.Role;
import harena.dev.banking.entity.Teacher;
import harena.dev.banking.entity.User;
import harena.dev.banking.entity.Student;
import harena.dev.banking.repository.CourseRepository;
import harena.dev.banking.repository.RoleRepository;
import harena.dev.banking.repository.TeacherRepository;
import harena.dev.banking.repository.UserRepository;
import harena.dev.banking.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Ajouter les rôles uniquement s'ils n'existent pas déjà
        if (roleRepository.count() == 0) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");

            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");

            roleRepository.saveAll(Arrays.asList(userRole, adminRole));
        }

        // Ajouter des utilisateurs à partir du fichier JSON
        if (userRepository.count() == 0) {
            List<User> users = objectMapper.readValue(new File("src/main/resources/users.json"), new TypeReference<List<User>>(){});
            for (User user : users) {
                user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
                user.setRole(roleRepository.findByName(user.getRole().getName()).orElse(null));
                userRepository.save(user);
            }
        }

        // Ajouter des enseignants à partir du fichier JSON
        if (teacherRepository.count() == 0) {
            List<Teacher> teachers = objectMapper.readValue(new File("src/main/resources/teachers.json"), new TypeReference<List<Teacher>>(){});
            for (Teacher teacher : teachers) {
                User user = userRepository.findByEmail(teacher.getUser().getEmail()).orElse(null);
                if (user != null) {
                    teacher.setUser(user);
                    teacherRepository.save(teacher);
                }
            }
        }

        // Ajouter des cours à partir du fichier JSON
        if (courseRepository.count() == 0) {
            List<Course> courses = objectMapper.readValue(new File("src/main/resources/courses.json"), new TypeReference<List<Course>>(){});
            for (Course course : courses) {
                Teacher teacher = teacherRepository.findById(course.getTeacher().getId()).orElse(null);
                if (teacher != null) {
                    course.setTeacher(teacher);
                    courseRepository.save(course);
                }
            }
        }

        // Ajouter des étudiants à partir du fichier JSON
        if (studentRepository.count() == 0) {
            List<Student> students = objectMapper.readValue(new File("src/main/resources/students.json"), new TypeReference<List<Student>>(){});
            for (Student student : students) {
                User user = userRepository.findByEmail(student.getUser().getEmail()).orElse(null);
                if (user != null) {
                    student.setUser(user);
                    studentRepository.save(student);
                }
            }
        }
    }
}
