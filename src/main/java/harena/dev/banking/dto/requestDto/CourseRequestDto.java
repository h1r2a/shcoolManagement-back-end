package harena.dev.banking.dto.requestDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequestDto {
    private String name;
    private int duration;
    private String description;
    private Long teacherId;
}
