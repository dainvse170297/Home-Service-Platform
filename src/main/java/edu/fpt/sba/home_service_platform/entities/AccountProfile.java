package edu.fpt.sba.home_service_platform.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    String firstName;
    String lastName;
    @Email
    @Column(unique = true)
    String email;
    @Column(unique = true)
    String phone;
    String address;
    String description;
    String coverImage;
    String avatar;
    boolean isActive;

}
