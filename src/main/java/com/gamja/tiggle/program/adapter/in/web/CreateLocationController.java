package com.gamja.tiggle.program.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.program.adapter.in.web.request.CreateLocationRequest;
import com.gamja.tiggle.program.application.port.in.CreateLocationCommand;
import com.gamja.tiggle.program.application.port.in.CreateLocationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/location")
public class CreateLocationController {
    private final CreateLocationUseCase createUseCase;

    @PostMapping("/create")
    public BaseResponse<String> create(@RequestBody CreateLocationRequest request){
        try {
            CreateLocationCommand command = CreateLocationCommand.builder()
                    .locationName(request.getLocationName())
                    .addressName(request.getAddressName())
                    .seatImg(request.getSeatImg())
                    .thumbnail(request.getThumbnail())
                    .build();
            createUseCase.createLocation(command);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }


    }
}
