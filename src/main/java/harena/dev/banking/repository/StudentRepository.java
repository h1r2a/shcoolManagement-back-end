package harena.dev.banking.repository;

import harena.dev.banking.entity.Course;
import harena.dev.banking.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("SELECT s FROM Student s WHERE :course NOT MEMBER OF s.courseList")
    List<Student> findStudentsNotInCourse(@Param("course") Course course);
}
