package com.gamja.tiggle.category.application.port.out;

import org.example.tiggle.category.domain.Category;
import org.example.tiggle.program.domain.Program;

public interface CreateCategoryPort {
    void createCategory(Category category);
}
