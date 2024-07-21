package com.gamja.tiggle.exchange.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.exchange.adapter.out.persistence.ExchangeEntity;
import com.gamja.tiggle.exchange.application.port.in.CreateExchangeApprovalCommand;
import com.gamja.tiggle.exchange.application.port.in.CreateExchangeApprovalUseCase;
import com.gamja.tiggle.exchange.application.port.out.ExchangePort;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.application.port.out.ReadReservationPort;
import com.gamja.tiggle.reservation.application.port.out.SaveReservationPort;
import com.gamja.tiggle.reservation.domain.Reservation;
import com.gamja.tiggle.reservation.domain.type.ReservationType;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class CreateExchangeApprovalService implements CreateExchangeApprovalUseCase {
    private final ExchangePort exchangePort;
    private final ReadReservationPort readReservationPort;
    private final SaveReservationPort saveReservationPort;


    @Override
    public ExchangeEntity findExchangeEntity(Long id) throws BaseException {
        return exchangePort.findById(id);
    }

    @Override
    @Transactional
    public void create(ExchangeEntity exchangeEntity, CreateExchangeApprovalCommand command) throws BaseException {
//        reservation 찾아오기
        ReservationEntity reservation1 = readReservationPort.read(Reservation.builder().id(command.getReservationId1()).build());
        ReservationEntity reservation2 = readReservationPort.read(Reservation.builder().id(command.getReservationId2()).build());

        if(!reservation1.getStatus().equals(ReservationType.COMPLETED) || !reservation2.getStatus().equals(ReservationType.COMPLETED)){
            throw new BaseException(BaseResponseStatus.UNAVAILABLE_EXCHANGE_OFFER);
        }

//        티켓 발행
        saveReservationPort.save(Reservation.builder()
                .userId(reservation1.getUser().getId())
                .programId(reservation1.getProgramEntity().getId())
                .seatId(reservation1.getSeatEntity().getId())
                .timesId(reservation1.getTimesEntity().getId())
                .ticketNumber(getTicketNumber())
                .totalPrice(reservation1.getTotalPrice())
                .status(ReservationType.IN_PROGRESS)
                .requestLimit(5)
                .build());

//        이전 티켓 상태 변경
        saveReservationPort.update(updateReservation(reservation1));
        saveReservationPort.update(updateReservation(reservation2));

//        교환 테이블 상태 변경 => isSuccess, isWatch 상태 바꿈  둘 다 true.
        exchangePort.update(exchangeEntity.successed(true));
    }

    private static String getTicketNumber() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String nowDate = LocalDateTime.now().format(formatter);
        String randomNumber = UUID.randomUUID().toString().substring(0, 4);
        return nowDate + "-" + randomNumber;
    }

    public static Reservation updateReservation(ReservationEntity reservation) {
        return Reservation.builder()
                .id(reservation.getId())
                .seatId(reservation.getSeatEntity().getId())
                .programId(reservation.getProgramEntity().getId())
                .timesId(reservation.getTimesEntity().getId())
                .userId(reservation.getUser().getId())
                .ticketNumber(reservation.getTicketNumber())
                .totalPrice(reservation.getTotalPrice())
                .status(ReservationType.EXCHANGED)
                .requestLimit(reservation.getRequestLimit()).build();
    }

    @Override
    public void reject(ExchangeEntity exchange) {
        exchangePort.update(exchange.successed(false));
    }
}
