package edu.fpt.sba.home_service_platform.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {

    String username;
    String password;
}
