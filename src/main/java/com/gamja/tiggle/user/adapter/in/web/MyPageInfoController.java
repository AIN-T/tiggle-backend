package com.gamja.tiggle.user.adapter.in.web;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.reservation.domain.Reservation;
import com.gamja.tiggle.user.application.port.in.SearchUserUseCase;
import com.gamja.tiggle.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "myPage-info-controller")
public class MyPageInfoController {
    private final SearchUserUseCase searchUserUseCase;

    public MyPageInfoController(SearchUserUseCase searchUserUseCase) {
        this.searchUserUseCase = searchUserUseCase;
    }

    @Operation(summary = "마이페이지 내 정보 프론트 엔드 전달 api 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully connecting my page", content = @Content(schema = @Schema(implementation = MyPageResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/my-page")
    public BaseResponse<User> myPage(Long userIdx) {
        User user;
        try {
            user = searchUserUseCase.findById(userIdx);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        return new BaseResponse<>(user);
    }
}

@Getter
@Setter
class MyPageResponse {
    private String token;
}
