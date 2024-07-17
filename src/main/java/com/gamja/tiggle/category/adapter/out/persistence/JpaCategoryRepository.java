package com.gamja.tiggle.category.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
