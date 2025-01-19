package edu.fpt.sba.home_service_platform.entities;

import edu.fpt.sba.home_service_platform.enums.CheckInStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String content;
    LocalDateTime createdAt;
    int rating;

    @ManyToOne
    Account account;

    @OneToOne
    @JoinColumn(name = "booking_post_id", referencedColumnName = "id")
    BookingPost bookingPost;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "feedback")
    List<FeedbackImage> feedbackImage;


}

