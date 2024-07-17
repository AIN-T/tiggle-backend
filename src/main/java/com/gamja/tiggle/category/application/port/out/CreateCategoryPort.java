package com.gamja.tiggle.category.application.port.out;

import com.gamja.tiggle.category.domain.Category;
import com.gamja.tiggle.program.domain.Program;

public interface CreateCategoryPort {
    void createCategory(Category category);
}
