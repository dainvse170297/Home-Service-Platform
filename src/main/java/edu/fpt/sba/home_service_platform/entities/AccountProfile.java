package edu.fpt.sba.home_service_platform.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotEmpty(message = "First name is required")
    String firstName;
    @NotEmpty(message = "Last name is required")
    String lastName;
    @Email
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String email;
    @Column(unique = true)
    @NotEmpty(message = "Phone is required")
    String phone;
    @NotEmpty(message = "Address is required")
    String address;
    String description;
    String coverImage;
    String avatar;

}
