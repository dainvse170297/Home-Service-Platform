package edu.fpt.sba.home_service_platform.entities;

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
public class FeedbackImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String url;
    @ManyToOne
    Feedback feedback;

}
