package apiEngine.DTOs;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Builder
public class LoginResponse {
   private String accessToken;
    private long tokenExpiration;
    private String profileId;
    private String deviceId;
    private String accountType;
    private String userType;
    private String encryptedProfileId;
    private String brand;
    private String platform;
    private Boolean isVerified;
    private Boolean newsLetter;
    private Boolean hasSocialLogin;
    private Boolean hasEmail;
    private String device;
    private String viewerId;
    private String linkedMVPD;
    private String unlinkedMVPD;
    private String dma;
    private String ipAddress;
    private String userAgent;
    private String gender;
    private String firstName;
    private String lastName;
    private String email;
}
