package com.gamja.tiggle.category.application.port.out;

import com.gamja.tiggle.category.domain.Category;

public interface CreateCategoryPort {
    void createCategory(Category category);
}
