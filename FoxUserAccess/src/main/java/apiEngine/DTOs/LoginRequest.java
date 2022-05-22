package apiEngine.DTOs;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class LoginRequest extends ResetField {
    private String password;
}
