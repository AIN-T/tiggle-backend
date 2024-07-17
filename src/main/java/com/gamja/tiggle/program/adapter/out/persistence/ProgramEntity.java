package com.gamja.tiggle.program.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.gamja.tiggle.category.adapter.out.persistence.CategoryEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="program")
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
}
