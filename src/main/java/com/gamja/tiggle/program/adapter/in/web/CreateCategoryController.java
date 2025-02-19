package com.gamja.tiggle.program.adapter.in.web;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import com.gamja.tiggle.program.adapter.in.web.request.CreateCategoryRequest;
import com.gamja.tiggle.program.application.port.in.CreateCategoryCommand;
import com.gamja.tiggle.program.application.port.in.CreateCategoryUseCase;
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
    @Operation(summary = "카테고리 등록")
    public BaseResponse<String> create(@RequestBody CreateCategoryRequest request) {
        // 예외 1. 카테고리 이름이 비어 있는지 확인
        if (request.getCategoryName() == null || request.getCategoryName().isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NOT_FOUND_CATEGORY);
        }
        CreateCategoryCommand command = CreateCategoryCommand.builder()
                .categoryName(request.getCategoryName())
                .build();
        try {
            createCategoryUseCase.createCategory(command);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
