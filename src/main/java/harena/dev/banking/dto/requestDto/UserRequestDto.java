package harena.dev.banking.dto.requestDto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String role;
    private String password;

}
