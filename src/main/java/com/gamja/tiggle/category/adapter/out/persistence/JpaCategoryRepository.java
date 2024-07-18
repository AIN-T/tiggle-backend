package com.gamja.tiggle.category.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.program.adapter.out.persistence.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
