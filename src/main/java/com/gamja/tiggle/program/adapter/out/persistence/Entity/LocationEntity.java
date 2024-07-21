package com.gamja.tiggle.program.adapter.out.persistence.Entity;

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
@Builder
@Getter
@Table(name = "location")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String locationName;
    private String addressName;
    private String thumbnail;
    private String seatImg;

    @Builder.Default
    @OneToMany(mappedBy = "locationEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ProgramEntity> programEntities = new ArrayList<>();

}
