package com.gamja.tiggle.program.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.program.domain.Category;

public interface CreateCategoryPort {
    void createCategory(Category category) throws BaseException;
}
