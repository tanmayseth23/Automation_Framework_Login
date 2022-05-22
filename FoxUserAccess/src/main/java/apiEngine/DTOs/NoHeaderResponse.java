package apiEngine.DTOs;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
public class NoHeaderResponse {

    private String errorType;
    private String errorMessage;
}
