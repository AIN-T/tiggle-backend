package com.gamja.tiggle.section.adapter.out.persistence;

import com.gamja.tiggle.grade.adapter.out.persistence.GradeEntity;
import com.gamja.tiggle.location.adapter.out.persistence.LocationEntity;
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
@Table(name = "section")
public class SectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sectionName;

    @ManyToOne(fetch = FetchType.LAZY)
    private LocationEntity location;
    @ManyToOne(fetch = FetchType.LAZY)
    private GradeEntity grade;

}
