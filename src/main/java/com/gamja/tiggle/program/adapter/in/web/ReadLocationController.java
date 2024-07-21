package com.gamja.tiggle.program.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.program.adapter.in.web.response.ReadLocationResponse;
import com.gamja.tiggle.program.adapter.in.web.response.ReadProgramResponse;
import com.gamja.tiggle.program.application.port.in.ReadLocationCommand;
import com.gamja.tiggle.program.application.port.in.ReadLocationUseCase;
import com.gamja.tiggle.program.domain.Location;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/location")
public class ReadLocationController {
    private final ReadLocationUseCase readUseCase;

    @GetMapping("/readProgram")
    public BaseResponse<List<ReadLocationResponse>> readProgram(@RequestParam Long locationId) {
        List<ReadLocationResponse> responses = null;
        try {
            ReadLocationCommand command = ReadLocationCommand.builder()
                    .locationId(locationId)
                    .build();
            List<Location> location = readUseCase.readProgramAll(command);
            responses = location.stream()
                    .map(l -> ReadLocationResponse.builder()
                            .id(l.getId())
                            .locationName(l.getLocationName())
                            .addressName(l.getAddressName())
                            .programNames(l.getProgramNames())
                            .build())
                    .collect(Collectors.toList());

        }
        catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
        return new BaseResponse<>(responses);
    }
}





