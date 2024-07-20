package com.gamja.tiggle.exchange.adapter.out.persistence;

import com.gamja.tiggle.common.BaseEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exchange")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isSuccess;
    private Boolean isWatch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation1_id", unique = false)
    private ReservationEntity reservation1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation2_id", unique = false)
    private ReservationEntity reservation2;

    public ExchangeEntity watched(){
        this.isWatch = true;

        return this;
    }

    public ExchangeEntity successed(Boolean state){
        this.isSuccess = state;

        return this;
    }
}
