package com.gamja.tiggle.program.application.port.in;

import com.gamja.tiggle.common.BaseException;

public interface CreateCategoryUseCase {
    void createCategory(CreateCategoryCommand command) throws BaseException;
}
