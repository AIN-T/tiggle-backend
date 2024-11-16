package com.gamja.tiggle.reservation.adapter.out.persistence.Entity;

import com.gamja.tiggle.program.adapter.out.persistence.Entity.GradeEntity;
import com.gamja.tiggle.program.adapter.out.persistence.Entity.LocationEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "section")
public class SectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sectionName;

    private int rowCount; // 행
    private int columnCount; // 열

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private LocationEntity locationEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id")
    private GradeEntity gradeEntity;
    @OneToMany(mappedBy = "sectionEntity")
    private List<SeatEntity> seatEntities = new ArrayList<>();

}
