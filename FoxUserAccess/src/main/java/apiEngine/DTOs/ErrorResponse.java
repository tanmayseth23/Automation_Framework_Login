package apiEngine.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * ErrorResponse DTO class.
 */
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorResponse {
    @JsonProperty("@context")
    private String context;
    @JsonProperty("@type")
    private String type;
    private String status;
    private int statusCode;
    private int errorCode;
    private String message;
    private String detail;
}
