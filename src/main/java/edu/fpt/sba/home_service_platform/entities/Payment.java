package edu.fpt.sba.home_service_platform.entities;

import edu.fpt.sba.home_service_platform.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    LocalDateTime createdAt;
    BigDecimal amount;
    PaymentStatus status;

    @OneToOne
    @JoinColumn(name = "booking_post_id", referencedColumnName = "id")
    BookingPost bookingPost;
}
