
package com.gamja.tiggle.program.adapter.out.persistence.Entity;

import com.gamja.tiggle.like.adapter.out.persistence.LikeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private LocalDateTime reservationOpenDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime programStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime programEndDate;

    // ProgramImage 1 : N
    @Builder.Default
//    @BatchSize(size = 5)
    @OneToMany(mappedBy = "programEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ProgramImageEntity> programImageEntities = new ArrayList<>();
    // Category N : 1
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    // Location N : 1
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private LocationEntity locationEntity;

    // Program: Like = 1 : N
    @OneToMany(mappedBy = "programEntity", fetch = FetchType.LAZY)
    private List<LikeEntity> likeEntities = new ArrayList<>();


    public ProgramEntity(Long id) {
        this.id = id;
    }

}
