package com.gamja.tiggle.like.adapter.in.web;


import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.like.adapter.in.web.response.ReadMyLikeResponse;
import com.gamja.tiggle.like.application.port.in.ReadLikeUseCase;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/like")
public class ReadLikeController {
    private final ReadLikeUseCase readLikeUseCase;

    @GetMapping("/my")
    @Operation(summary = "나의 좋아요 조회", description = "사용자가 프로그램에 좋아요 한 공연들을 조회하는 API 입니다.")
    public BaseResponse<List<ReadMyLikeResponse>> readMyLikes(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = customUserDetails.getUser();

        List<ReadMyLikeResponse> myLikeResponseList = readLikeUseCase.readMyLikes(user);

        return new BaseResponse<>(myLikeResponseList);
    }

}
