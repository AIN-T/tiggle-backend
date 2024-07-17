package com.gamja.tiggle.program.adapter.out.persistence;

import org.example.tiggle.category.adapter.out.persistence.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaProgramRepository extends JpaRepository<ProgramEntity, Long> {

}
