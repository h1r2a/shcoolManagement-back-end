package harena.dev.banking.config;

import harena.dev.banking.entity.Course;
import harena.dev.banking.entity.Role;
import harena.dev.banking.entity.Teacher;
import harena.dev.banking.entity.User;
import harena.dev.banking.repository.CourseRepository;
import harena.dev.banking.repository.RoleRepository;
import harena.dev.banking.repository.TeacherRepository;
import harena.dev.banking.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

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

    @PostConstruct
    public void init() {
        // Ajouter les rôles uniquement s'ils n'existent pas déjà
        if (roleRepository.count() == 0) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");

            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");

            roleRepository.saveAll(Arrays.asList(userRole, adminRole));
        }

        // Ajouter des données de test uniquement si la table User est vide
        if (userRepository.count() == 0) {
            // Créer un utilisateur
            User user = new User();
            user.setEmail("user@example.com");
            user.setPassword("password");
            user.setRole(roleRepository.findByName("ROLE_USER").orElse(null));
            userRepository.save(user);

            // Créer un enseignant associé à cet utilisateur
            Teacher teacher = new Teacher();
            teacher.setFirstName("John");
            teacher.setLastName("Doe");
            teacher.setPhoneNumber("1234567890");
            teacher.setUser(user);
            teacherRepository.save(teacher);

            // Créer un cours associé à cet enseignant
            Course course = new Course();
            course.setName("Introduction to Programming");
            course.setDuration(60); // Durée en minutes
            course.setDescription("This course covers the basics of programming concepts.");
            course.setTeacher(teacher);
            courseRepository.save(course);
        }
    }
}
