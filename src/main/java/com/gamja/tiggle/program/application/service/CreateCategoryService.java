package com.gamja.tiggle.program.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.program.adapter.out.persistence.JpaCategoryRepository;
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
    private final JpaCategoryRepository jpaCategoryRepository;

    @Override
    public void createCategory(CreateCategoryCommand command) throws BaseException {
        // 예외 2. 중복된 카테고리 확인
        if (jpaCategoryRepository.existsByCategoryName(command.getCategoryName())){
            throw new BaseException(BaseResponseStatus.DUPLICATE_CATEGORY);
        }
        Category category = Category.builder()
                .categoryName(command.getCategoryName())
                .build();
        try {
            createCategoryPort.createCategory(category);
        } catch (BaseException e) {
            throw new BaseException(BaseResponseStatus.CATEGORY_CREATION_FAILED);
        }

    }
}
