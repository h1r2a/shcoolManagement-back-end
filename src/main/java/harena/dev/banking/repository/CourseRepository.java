package harena.dev.banking.repository;

import harena.dev.banking.entity.Course;
import harena.dev.banking.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query("SELECT c FROM Course c WHERE :student NOT MEMBER OF c.studentList ")
    List<Course> findCourseNotInCourseList(@Param("student") Student student);
}
