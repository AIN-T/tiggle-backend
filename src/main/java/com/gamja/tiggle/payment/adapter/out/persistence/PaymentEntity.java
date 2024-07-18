package com.gamja.tiggle.payment.adapter.out.persistence;

import com.gamja.tiggle.reservation.adapter.out.persistence.ReservationEntity;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import com.gamja.tiggle.reservation.domain.Reservation;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private Integer ticketPrice;
    private Integer usePoint;
    private Integer fee;
    private String string;
    private Boolean verify;

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    //private User user;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private ReservationEntity reservation;

    @Column(updatable = false, nullable = false)
    private Date createdAt;

    private Date verifiedAt;

    @PrePersist
    void createdAt() {
        this.createdAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void verifiedAt() {
        this.verifiedAt = Timestamp.from(Instant.now());
    }

}
