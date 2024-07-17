package com.gamja.tiggle.category.application.service;

import lombok.RequiredArgsConstructor;
import com.gamja.tiggle.category.application.port.in.CreateCategoryCommand;
import com.gamja.tiggle.category.application.port.in.CreateCategoryUseCase;
import com.gamja.tiggle.category.application.port.out.CreateCategoryPort;
import com.gamja.tiggle.category.domain.Category;
import com.gamja.tiggle.common.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class CreateCategoryService implements CreateCategoryUseCase {
    private final CreateCategoryPort createCategoryPort;

    @Override
    public void createCategory(CreateCategoryCommand command) {
        Category category = Category.builder()
                .categoryName(command.getCategoryName())
                .build();
        createCategoryPort.createCategory(category);
    }
}
