package edu.fpt.sba.home_service_platform.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {

    String username;
    String firstName;
    String lastName;
    String email;
    String phone;
    String address;
    String role;
    String coverImage;
    String avatar;
    boolean active;
}
