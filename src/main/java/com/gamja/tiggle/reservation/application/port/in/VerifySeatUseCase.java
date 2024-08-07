package com.gamja.tiggle.reservation.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.reservation.adapter.in.web.response.VerifySeatResponse;

import java.util.List;

public interface VerifySeatUseCase {

    VerifySeatResponse verifySeat(VerifySeatCommand command) throws BaseException;
}
