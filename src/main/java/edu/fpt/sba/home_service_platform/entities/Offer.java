package edu.fpt.sba.home_service_platform.entities;

import edu.fpt.sba.home_service_platform.enums.OfferStatus;
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
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    BigDecimal offerPrice;
    int offerDuration;
    LocalDateTime createdAt;
    String note;
    OfferStatus offerStatus;
    @ManyToOne
    BookingPost bookingPost;
    @ManyToOne
    Account account;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "offer")
    List<CheckIn> checkIn;


}
