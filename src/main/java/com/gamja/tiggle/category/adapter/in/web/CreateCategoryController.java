package com.gamja.tiggle.category.adapter.in.web;


import lombok.RequiredArgsConstructor;
import com.gamja.tiggle.category.adapter.in.web.request.CreateCategoryRequest;
import com.gamja.tiggle.category.application.port.in.CreateCategoryCommand;
import com.gamja.tiggle.category.application.port.in.CreateCategoryUseCase;
import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/category")
public class CreateCategoryController {
    private final CreateCategoryUseCase createCategoryUseCase;

    @PostMapping("/create")
    public BaseResponse<String> create(@RequestBody CreateCategoryRequest request) throws BaseException {
        // 예외 1. 카테고리 이름이 비어 있는지 확인
        if (request.getCategoryName() == null || request.getCategoryName().isEmpty()) {
            throw new BaseException(BaseResponseStatus.FAIL);
        }
        CreateCategoryCommand command = CreateCategoryCommand.builder()
                .categoryName(request.getCategoryName())
                .build();
        createCategoryUseCase.createCategory(command);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
