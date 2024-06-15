package harena.dev.banking.dto.requestDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String email;
    private String role;
    private String password;

}
