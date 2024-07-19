package com.gamja.tiggle.reservation.adapter.out.persistence.Entity;

import com.gamja.tiggle.exchange.adapter.out.persistence.ExchangeEntity;
import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramEntity;
import com.gamja.tiggle.reservation.domain.type.ReservationType;
import com.gamja.tiggle.user.adapter.out.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


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
    @JoinColumn(name = "program_id")
    private ProgramEntity programEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private SeatEntity seatEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "times_id")
    private TimesEntity timesEntity;

    private String ticketNumber;
    private String payMethod;
    private Integer totalPrice;
    private Integer requestLimit;

    @Enumerated(EnumType.STRING)
    private ReservationType status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation1")
    private List<ExchangeEntity> exchangeEntity1List;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation2")
    private List<ExchangeEntity> exchangeEntity2List;
}
