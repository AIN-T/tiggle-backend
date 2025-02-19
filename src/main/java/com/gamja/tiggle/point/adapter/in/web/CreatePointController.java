package com.gamja.tiggle.point.adapter.in.web;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.point.adapter.in.web.request.CreatePointHistoryRequest;
import com.gamja.tiggle.point.application.port.in.CreatePointHistoryCommand;
import com.gamja.tiggle.point.application.port.in.CreatePointHistoryUseCase;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class CreatePointController {
    private final CreatePointHistoryUseCase createPointHistoryUseCase;

    @PostMapping
    @Operation(summary = "포인트 획득 및 사용", description = "로그인한 사용자의 포인트 획득/사용 내역을 DB에 저장하는 api입니다.")
    BaseResponse writePointHistory(@RequestBody CreatePointHistoryRequest request, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = null;
        //CustomUserDetails 를 확인하여 로그인한 사용자의 정보와 Role을 확인
        if (customUserDetails != null) {
            user = customUserDetails.getUser();
        }
        else {
            return new BaseResponse(BaseResponseStatus.NOT_FOUND_USER);
        }

        try {
            User findUser = createPointHistoryUseCase.findByUserId(user.getId());
            CreatePointHistoryCommand command = CreatePointHistoryCommand.builder()
                    .userId(findUser.getId())
                    .value(request.getValue())
                    .hasPoint(findUser.getPoint())
                    .description(request.getDescription())
                    .build();
            createPointHistoryUseCase.create(command);

        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        return new BaseResponse(BaseResponseStatus.SUCCESS);
    }
}
