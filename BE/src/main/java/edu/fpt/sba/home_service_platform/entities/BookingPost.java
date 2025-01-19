package edu.fpt.sba.home_service_platform.entities;

import edu.fpt.sba.home_service_platform.enums.BookingPostStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    String description;
    BigDecimal estimatePrice;
    LocalDateTime createdAt;
    LocalDateTime startTime;
    double estimateDuration;
    String location;
    @Enumerated(EnumType.STRING)
    BookingPostStatus bookingPostStatus;
    @ManyToOne
    Account account;
    @ManyToOne
    Service service;

}
