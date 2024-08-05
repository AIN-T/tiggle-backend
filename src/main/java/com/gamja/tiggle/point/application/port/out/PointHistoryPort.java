package com.gamja.tiggle.point.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.point.domain.PointHistory;
import org.springframework.stereotype.Service;

@Service
public interface PointHistoryPort {
    void save(PointHistory pointHistory) throws BaseException;
}

