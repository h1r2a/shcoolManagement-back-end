package harena.dev.banking.dto.responseDto;

import harena.dev.banking.entity.Teacher;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
    private  Long id ;
    private String firstName;
    private String lastName;
    private String level;
    private Long userId;
    private List<String> course;

}
