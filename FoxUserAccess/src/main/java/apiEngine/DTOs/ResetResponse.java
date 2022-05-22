package apiEngine.DTOs;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
public class ResetResponse {

    private String message;
    private String detail;
}
