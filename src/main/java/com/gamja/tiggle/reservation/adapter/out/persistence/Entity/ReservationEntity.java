package com.gamja.tiggle.reservation.adapter.out.persistence.Entity;

import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramEntity;
import com.gamja.tiggle.reservation.domain.type.ReservationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "reservation")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProgramEntity programEntity;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    private SeatEntity seatEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private TimesEntity timesEntity;

    private String ticketNumber;
    private String payMethod;
    private Integer totalPrice;
    private Integer requestLimit;

    @Enumerated(EnumType.STRING)
    private ReservationType status;
}
