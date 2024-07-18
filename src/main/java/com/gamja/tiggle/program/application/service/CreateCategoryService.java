package com.gamja.tiggle.program.application.service;

import lombok.RequiredArgsConstructor;
import com.gamja.tiggle.program.application.port.in.CreateCategoryCommand;
import com.gamja.tiggle.program.application.port.in.CreateCategoryUseCase;
import com.gamja.tiggle.program.application.port.out.CreateCategoryPort;
import com.gamja.tiggle.program.domain.Category;
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
