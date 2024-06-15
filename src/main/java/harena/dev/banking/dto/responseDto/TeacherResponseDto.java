package harena.dev.banking.dto.responseDto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponseDto {
    private Long id;
    private String firstName;
    private String lastname;
    private String phoneNumber;
    private  Long userId;
    private List<CourseResponseDto> courses;

}
