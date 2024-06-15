package harena.dev.banking.dto.requestDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequestDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private long userId;
}
