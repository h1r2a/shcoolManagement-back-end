package harena.dev.banking.dto.responseDto;

import harena.dev.banking.entity.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class StudentResponseDto {
    private  Long id ;
    private String firstName;
    private String lastName;
    private String level;
    private Long userId;
    private List<CourseResponseDto> course;

}
