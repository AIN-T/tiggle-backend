package com.gamja.tiggle.category.adapter.out.persistence;

import org.example.tiggle.program.adapter.out.persistence.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query("SELECT c FROM CategoryEntity c JOIN FETCH c.programEntities WHERE c.id = :id")
    Optional<CategoryEntity> findByIdWithPrograms(@Param("id") Long id);
}
