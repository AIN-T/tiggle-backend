package com.gamja.tiggle.like.adapter.in.web;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.like.application.port.in.CreateLikeCommand;
import com.gamja.tiggle.like.application.port.in.CreateLikeUseCase;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/like")
public class CreateLikeController {

    private final CreateLikeUseCase createLikeUseCase;

    @PostMapping("/{programId}")
    @Operation(summary = "좋아요 토글", description = "사용자가 프로그램에 좋아요를 추가하거나, 기존 좋아요를 취소할 수 있는 API 입니다.")
    public BaseResponse<BaseResponseStatus> createLike(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long programId) {
        User user = customUserDetails.getUser();

        CreateLikeCommand command = CreateLikeCommand.builder()
                .user(user)
                .programId(programId)
                .build();

        boolean isLike;

        try {
            isLike = createLikeUseCase.toggleLike(command);

        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse<>(isLike ? BaseResponseStatus.LIKE_ADD_SUCCESS : BaseResponseStatus.LIKE_DELETE_SUCCESS);
    }
}
