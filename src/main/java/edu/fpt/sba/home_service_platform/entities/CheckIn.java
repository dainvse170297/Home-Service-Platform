package edu.fpt.sba.home_service_platform.entities;

import edu.fpt.sba.home_service_platform.enums.CheckInStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    LocalDateTime createdAt;
    String description;

    CheckInStatus checkInStatus;

    @ManyToOne
    Offer offer;

}
