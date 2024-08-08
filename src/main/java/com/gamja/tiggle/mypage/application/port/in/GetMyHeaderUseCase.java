package com.gamja.tiggle.mypage.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.mypage.domain.MyHeaderInfo;

public interface GetMyHeaderUseCase {
    MyHeaderInfo getByUserId(Long id) throws BaseException;
}
