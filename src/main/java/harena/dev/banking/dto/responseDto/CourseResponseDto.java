package harena.dev.banking.dto.responseDto;

import harena.dev.banking.entity.Student;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CourseResponseDto {
    private Long id;
    private String name;
    private int duration;
    private String description;
    private String teacherName;
    private List<String> students = new ArrayList<>();
}
