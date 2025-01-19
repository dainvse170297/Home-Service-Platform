package edu.fpt.sba.home_service_platform.entities;

import edu.fpt.sba.home_service_platform.enums.MembershipRegisterStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MembershipRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDateTime startDate;
    LocalDateTime endDate;

    MembershipRegisterStatus status;

    @OneToOne (cascade = CascadeType.ALL)
    Account account;
    @ManyToOne
    Membership memberships;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "membershipRegister")
    List<MembershipRegisterPayment> membershipRegisterPayments;

}
