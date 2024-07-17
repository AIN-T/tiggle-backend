package com.gamja.tiggle.category.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.example.tiggle.category.application.port.out.CreateCategoryPort;
import org.example.tiggle.category.application.port.out.ReadCategoryPort;
import org.example.tiggle.category.domain.Category;
import org.example.tiggle.common.annotation.PersistenceAdapter;
import org.example.tiggle.program.adapter.out.persistence.JpaProgramImageRepository;
import org.example.tiggle.program.adapter.out.persistence.JpaProgramRepository;
import org.example.tiggle.program.adapter.out.persistence.ProgramEntity;
import org.example.tiggle.program.adapter.out.persistence.ProgramImageEntity;
import org.example.tiggle.program.application.port.out.CreateProgramPort;
import org.example.tiggle.program.application.port.out.DeleteProgramPort;
import org.example.tiggle.program.domain.Program;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CreateCategoryPort, ReadCategoryPort {
    private final JpaCategoryRepository jpaCategoryRepository;

    @Override
    public Category readCategory(Category category) {
        Optional<CategoryEntity> result = jpaCategoryRepository.findByIdWithPrograms(category.getId());

        if (result.isPresent()) {
            CategoryEntity entity = result.get();
            Category returnCategory = Category.builder()
                    .id(entity.getId())
                    .categoryName(entity.getCategoryName())
                    .programs(entity.getProgramEntities().stream().map((programEntity) -> programEntity.getProgramName()).toList())
                    .build();
            return returnCategory;
        }
        return null;
    }

    @Override
    public void createCategory(Category category) {
        CategoryEntity entity = CategoryEntity.builder()
                .categoryName(category.getCategoryName())
                .build();
        jpaCategoryRepository.save(entity);
    }
}
