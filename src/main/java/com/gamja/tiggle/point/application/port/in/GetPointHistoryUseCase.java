package com.gamja.tiggle.point.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.point.domain.PointHistory;

import java.util.List;

public interface GetPointHistoryUseCase {
    List<PointHistory> getByUserId(GetPointHistoryCommand command) throws BaseException;
}
