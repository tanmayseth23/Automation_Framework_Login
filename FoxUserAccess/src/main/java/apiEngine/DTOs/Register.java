package apiEngine.DTOs;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class Register extends LoginRequest{

    private String gender;
    private String firstName;
    private String lastName;
}
