package com.gamja.tiggle.point.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.point.application.port.in.CreatePointHistoryCommand;
import com.gamja.tiggle.point.application.port.in.CreatePointHistoryUseCase;
import com.gamja.tiggle.point.application.port.out.PointHistoryPort;
import com.gamja.tiggle.point.domain.GetOrUse;
import com.gamja.tiggle.point.domain.PointHistory;
import com.gamja.tiggle.user.application.port.out.UserPersistencePort;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePointHistoryService implements CreatePointHistoryUseCase {
    private final PointHistoryPort pointHistoryPort;
    private final UserPersistencePort userPersistencePort;

    @Override
    public void create(CreatePointHistoryCommand command) throws BaseException {
        PointHistory pointHistory = null;

        if (command.getValue()>=0) {
            pointHistory = PointHistory.builder()
                    .userId(command.getUserId())
                    .value(command.getValue())
                    .remainPoint(command.getHasPoint()+command.getValue())
                    .description(command.getDescription())
                    .getOrUse(GetOrUse.GET)
                    .build();
        } else if (command.getValue()<0) {
            if (command.getHasPoint()<Math.abs(command.getValue())) {
                throw new BaseException(BaseResponseStatus.POINT_NOT_ENOUGH);
            }
            else {
                pointHistory = PointHistory.builder()
                        .userId(command.getUserId())
                        .value(command.getValue())
                        .remainPoint(command.getHasPoint()+command.getValue())
                        .description(command.getDescription())
                        .getOrUse(GetOrUse.USE)
                        .build();
            }
        }
        pointHistoryPort.save(pointHistory);
        userPersistencePort.savePoint(command.getUserId(), command.getValue());
    }

    @Override
    public User findByUserId(Long id) throws BaseException {
        return userPersistencePort.searchUser(id);
    }
}

