package edu.fpt.sba.home_service_platform.entities;

import edu.fpt.sba.home_service_platform.enums.BookingPostStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    BookingPostStatus bookingPostStatus;

    @ManyToOne
    @JoinColumn(name = "author_id")
    Account account;

    @ManyToOne
    Facility facility;
    @OneToMany(mappedBy = "bookingPost")
    List<Offer> offers;
    @OneToOne(mappedBy = "bookingPost")
    Payment payment;

    @OneToOne(mappedBy = "bookingPost")
    Feedback feedback;
}
