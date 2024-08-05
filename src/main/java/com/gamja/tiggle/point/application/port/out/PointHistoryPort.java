package com.gamja.tiggle.point.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.point.adapter.out.persistence.PointEntity;
import com.gamja.tiggle.point.domain.GetOrUse;
import com.gamja.tiggle.point.domain.PointHistory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface PointHistoryPort {
    void save(PointHistory pointHistory) throws BaseException;

    List<PointHistory> findPointHistorybyUserId(Long userId, GetOrUse method);
}

