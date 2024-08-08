package com.gamja.tiggle.payment.adapter.out.persistence;

import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private Integer ticketPrice;
    private Integer usePoint;
    private Integer fee;
    private String payType;
    private Boolean verify;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private ReservationEntity reservationEntity;

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
