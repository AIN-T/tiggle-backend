package com.gamja.tiggle.program.adapter.out.persistence;

import com.gamja.tiggle.program.adapter.out.persistence.Entity.CategoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Boolean existsByCategoryName(String categoryName);

}
