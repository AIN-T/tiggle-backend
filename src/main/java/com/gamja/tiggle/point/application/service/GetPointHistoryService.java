package com.gamja.tiggle.point.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.point.adapter.out.persistence.PointEntity;
import com.gamja.tiggle.point.application.port.in.CreatePointHistoryCommand;
import com.gamja.tiggle.point.application.port.in.CreatePointHistoryUseCase;
import com.gamja.tiggle.point.application.port.in.GetPointHistoryCommand;
import com.gamja.tiggle.point.application.port.in.GetPointHistoryUseCase;
import com.gamja.tiggle.point.application.port.out.PointHistoryPort;
import com.gamja.tiggle.point.domain.GetOrUse;
import com.gamja.tiggle.point.domain.PointHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetPointHistoryService implements GetPointHistoryUseCase {
    private final PointHistoryPort pointHistoryPort;

    @Override
    public List<PointHistory> getByUserId(GetPointHistoryCommand command) throws BaseException {
        return pointHistoryPort.findPointHistorybyUserId(command.getUserId(), command.getGetOrUse());
    }
}

