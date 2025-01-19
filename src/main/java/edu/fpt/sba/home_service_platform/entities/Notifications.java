package edu.fpt.sba.home_service_platform.entities;

import edu.fpt.sba.home_service_platform.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    String content;
    boolean isRead = false;
    NotificationType type;

    @ManyToOne
    Account account;
}
