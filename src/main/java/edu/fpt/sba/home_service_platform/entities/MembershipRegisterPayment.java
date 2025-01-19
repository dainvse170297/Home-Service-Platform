package edu.fpt.sba.home_service_platform.entities;

import edu.fpt.sba.home_service_platform.enums.MemberShipPaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MembershipRegisterPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDateTime createdAt;
    BigDecimal amount;

    MemberShipPaymentStatus memberShipPaymentStatus;
    @ManyToOne
    MembershipRegister membershipRegister;
}
