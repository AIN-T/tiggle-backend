package com.gamja.tiggle.reservation.adapter.out.persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramEntity;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "times")
public class TimesEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProgramEntity programEntity;

    private int round;
    private LocalDateTime date;
    private LocalDateTime limitEnterTime;

    public TimesEntity(Long id) {
        this.id = id;
    }
}
