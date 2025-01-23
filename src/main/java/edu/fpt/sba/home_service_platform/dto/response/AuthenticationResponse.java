package edu.fpt.sba.home_service_platform.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {

    String accessToken;
    String refreshToken;
}
