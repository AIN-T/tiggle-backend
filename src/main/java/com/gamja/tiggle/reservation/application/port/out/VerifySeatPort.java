package com.gamja.tiggle.reservation.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.reservation.domain.Seat;

import java.util.List;

public interface VerifySeatPort {

    void verifySeat(Seat seat) throws BaseException;
}
