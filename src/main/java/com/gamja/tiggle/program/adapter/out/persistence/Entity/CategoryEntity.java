package com.gamja.tiggle.program.adapter.out.persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="category")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;

    @Builder.Default
    @OneToMany(mappedBy = "categoryEntity", fetch = FetchType.LAZY)
    List<ProgramEntity> programEntities = new ArrayList<>();

}
