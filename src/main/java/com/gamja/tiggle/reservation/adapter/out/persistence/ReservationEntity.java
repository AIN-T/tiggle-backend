package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.program.adapter.out.persistence.ProgramEntity;
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
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProgramEntity program;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private UserEntity user;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private SeatEntity seat;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private TimesEntity times;

    private String ticketNumber;
    private String payMethod;
    private Integer totalPrice;

    @Enumerated(EnumType.STRING)
    private ReservationType status;

    private Integer requestLimit;
    private Integer refund;

}
