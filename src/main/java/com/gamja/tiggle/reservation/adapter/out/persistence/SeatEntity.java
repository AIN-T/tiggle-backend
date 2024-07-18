package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.program.adapter.out.persistence.GradeEntity;
import com.gamja.tiggle.program.adapter.out.persistence.LocationEntity;
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
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private LocationEntity location;

    @ManyToOne(fetch = FetchType.LAZY)
    private GradeEntity grade;
    private String section;
    private int seatNumber;
}
