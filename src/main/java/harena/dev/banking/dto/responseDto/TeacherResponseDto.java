package harena.dev.banking.dto.responseDto;


import lombok.Data;

import java.util.List;

@Data
public class TeacherResponseDto {
    private Long id;
    private String firstName;
    private String lastname;
    private String phoneNumber;
    private  Long userId;
    private List<CourseResponseDto> courses;

}
