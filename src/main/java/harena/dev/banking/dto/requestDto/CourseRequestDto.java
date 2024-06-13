package harena.dev.banking.dto.requestDto;

import lombok.Data;

@Data
public class CourseRequestDto {
    private String name;
    private int duration;
    private String description;
    private Long teacherId;
}
