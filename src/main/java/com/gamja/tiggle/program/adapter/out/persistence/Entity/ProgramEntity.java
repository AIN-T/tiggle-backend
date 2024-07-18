package com.gamja.tiggle.program.adapter.out.persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "program")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String programName;
    private String programInfo;

    private int age;
    private int runtime;

    private LocalDateTime reservationOpenDate;
    private LocalDateTime programStartDate;
    private LocalDateTime programEndDate;

    // ProgramImage 1 : N
    @Builder.Default
    @OneToMany(mappedBy = "programEntity", fetch = FetchType.LAZY)
    List<ProgramImageEntity> programImageEntities = new ArrayList<>();
    // Category N : 1
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private LocationEntity locationEntity;


    public ProgramEntity(Long id) {
        this.id = id;
    }
}
