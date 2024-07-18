package com.gamja.tiggle.program.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.program.adapter.out.persistence.Entity.CategoryEntity;
import lombok.RequiredArgsConstructor;
import com.gamja.tiggle.program.application.port.out.CreateCategoryPort;
import com.gamja.tiggle.program.domain.Category;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;

@PersistenceAdapter
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CreateCategoryPort {
    private final JpaCategoryRepository jpaCategoryRepository;

    @Override
    public void createCategory(Category category) throws BaseException {
        CategoryEntity entity = CategoryEntity.builder()
                .categoryName(category.getCategoryName())
                .build();
        jpaCategoryRepository.save(entity);

    }
}
