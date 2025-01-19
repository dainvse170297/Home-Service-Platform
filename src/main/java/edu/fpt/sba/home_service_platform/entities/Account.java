package edu.fpt.sba.home_service_platform.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true, nullable = false)
    String username;
    String password;
    boolean isActive;
    @OneToOne(cascade = CascadeType.ALL)
    AccountProfile accountProfile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "account")
    List<BookingPost> bookingPosts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "account")
    List<Notifications> notifications;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "account")
    List<Feedback> feedbacks;

    @ManyToOne
    Role role;

}

