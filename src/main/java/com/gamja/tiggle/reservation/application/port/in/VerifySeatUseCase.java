package com.gamja.tiggle.reservation.application.port.in;

import com.gamja.tiggle.common.BaseException;

import java.util.List;

public interface VerifySeatUseCase {

    void verifySeat(VerifySeatCommand command) throws BaseException;
}
