package com.gamja.tiggle.point.adapter.in.web;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.point.application.port.in.CreatePointHistoryUseCase;
import com.gamja.tiggle.point.application.port.in.GetPointHistoryCommand;
import com.gamja.tiggle.point.application.port.in.GetPointHistoryUseCase;
import com.gamja.tiggle.point.domain.GetOrUse;
import com.gamja.tiggle.point.domain.PointHistory;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class GetPointController {
    private final GetPointHistoryUseCase getPointHistoryUseCase;

    @PostMapping("/history")
    @Operation(summary = "사용자의 포인트 내역 조회", description = "로그인한 사용자의 포인트 수여/사용 내역, 전체 조회")
    BaseResponse getPointHistory(@RequestBody GetPointHistoryCommand request , @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = null;
        //CustomUserDetails 를 확인하여 로그인한 사용자의 정보와 Role을 확인
        if (customUserDetails != null) {
            user = customUserDetails.getUser();
        }
        else {
            return new BaseResponse(BaseResponseStatus.NOT_FOUND_USER);
        }

        List<PointHistory> pointHistoryList;
        GetPointHistoryCommand command = GetPointHistoryCommand.builder()
                .userId(request.getUserId())
                .getOrUse(request.getGetOrUse())
                .build();
        try {
                pointHistoryList = getPointHistoryUseCase.getByUserId(command);
            {

            }

        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        return new BaseResponse<>(pointHistoryList);
    }
}
