package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SeatEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.TimesEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.ReservationRepository;
import com.gamja.tiggle.reservation.application.port.out.SaveReservationPort;
import com.gamja.tiggle.reservation.domain.Reservation;
import com.gamja.tiggle.reservation.domain.type.ReservationType;
import com.gamja.tiggle.user.adapter.out.persistence.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@PersistenceAdapter
@RequiredArgsConstructor
public class SaveReservationAdapter implements SaveReservationPort {

    private final ReservationRepository reservationRepository;
    private final RedisTemplate redisTemplate;

    @Override
    public void save(Reservation reservation) {

        saveToRedisReservation(reservation);
        lockSeat(reservation);
    }

    private void saveToRedisReservation(Reservation reservation) {
        String key = "reservation:" + reservation.getTicketNumber();

        Map<String, String> redisData = new HashMap<>();
        redisData.put("userId", reservation.getUserId().toString());
        redisData.put("seatId", reservation.getSeatId().toString());
        redisData.put("sectionId", reservation.getSectionId().toString());
        redisData.put("programId", reservation.getProgramId().toString());
        redisData.put("timesId", reservation.getTimesId().toString());
        redisData.put("totalPrice", reservation.getTotalPrice().toString());
        redisData.put("status", "IN_PROGRESS");

        redisTemplate.opsForHash().putAll(key, redisData);

        redisTemplate.expire(key, Duration.ofMinutes(10));
    }

    private void lockSeat(Reservation reservation) {
        String key = "seat:" + reservation.getProgramId() + ":" + reservation.getTimesId() + ":" + reservation.getSectionId() + ":" + reservation.getSeatId();

        redisTemplate.opsForHash().put(key, "status", "LOCKED");

        redisTemplate.expire(key, Duration.ofMinutes(10));
    }


    private static ReservationEntity from(Reservation reservation) {
        return ReservationEntity.builder()
                .programEntity(new ProgramEntity(reservation.getProgramId()))
                .timesEntity(new TimesEntity(reservation.getTimesId()))
                .seatEntity(new SeatEntity(reservation.getSeatId()))
                .ticketNumber(reservation.getTicketNumber())
                .requestLimit(reservation.getRequestLimit())
                .totalPrice(reservation.getTotalPrice())
                .user(new UserEntity(reservation.getUserId()))
                .ticketNumber(reservation.getTicketNumber())
                .status(ReservationType.IN_PROGRESS)
                .build();
    }

    @Override
    public void update(Reservation reservation) {

        ReservationEntity reservationEntity = updateFrom(reservation);
        reservationRepository.save(reservationEntity);
    }

    private static ReservationEntity updateFrom(Reservation reservation) {
        return ReservationEntity.builder()
                .id(reservation.getId())
                .programEntity(new ProgramEntity(reservation.getProgramId()))
                .user(new UserEntity(reservation.getUserId()))
                .seatEntity(new SeatEntity(reservation.getSeatId()))
                .timesEntity(new TimesEntity(reservation.getTimesId()))
                .ticketNumber(reservation.getTicketNumber())
                .totalPrice(reservation.getTotalPrice())
                .requestLimit(reservation.getRequestLimit())
                .status(reservation.getStatus())
                .available(reservation.getAvailable())
                .payType(reservation.getPayType())
                .build();
    }
}
