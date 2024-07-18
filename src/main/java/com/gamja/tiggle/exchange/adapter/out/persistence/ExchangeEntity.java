package com.gamja.tiggle.exchange.adapter.out.persistence;


import com.gamja.tiggle.common.BaseEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation1_id")
    private ReservationEntity reservation1;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation2_id")
    private ReservationEntity reservation2;
}
