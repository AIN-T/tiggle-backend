package com.gamja.tiggle.user.application.port.in;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.reservation.domain.Reservation;
import com.gamja.tiggle.user.domain.User;

import java.util.List;

public interface SearchUserUseCase {
    User findById(Long id) throws BaseException;
}
