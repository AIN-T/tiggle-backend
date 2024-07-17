package com.gamja.tiggle.category.application.service;

import lombok.RequiredArgsConstructor;
import org.example.tiggle.category.application.port.in.CreateCategoryCommand;
import org.example.tiggle.category.application.port.in.CreateCategoryUseCase;
import org.example.tiggle.category.application.port.out.CreateCategoryPort;
import org.example.tiggle.category.domain.Category;
import org.example.tiggle.common.annotation.UseCase;
import org.example.tiggle.program.application.port.in.CreateProgramCommand;
import org.example.tiggle.program.application.port.in.CreateProgramUseCase;
import org.example.tiggle.program.application.port.in.DeleteProgramCommand;
import org.example.tiggle.program.application.port.in.DeleteProgramUseCase;
import org.example.tiggle.program.application.port.out.CreateProgramPort;
import org.example.tiggle.program.application.port.out.DeleteProgramPort;
import org.example.tiggle.program.application.port.out.S3UploadPort;
import org.example.tiggle.program.domain.Program;

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
