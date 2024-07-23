package com.gamja.tiggle.payment.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.payment.application.port.out.PaymentPersistencePort;
import com.gamja.tiggle.payment.domain.Payment;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.ReservationRepository;
import com.gamja.tiggle.reservation.domain.type.ReservationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements PaymentPersistencePort {
    private final JpaPaymentRepository jpaPaymentRepository;
    private final ReservationRepository jpaReservationRepository;


    @Override
    public void savePayment(Payment payment) throws BaseException {
        ReservationEntity result = jpaReservationRepository.findById(payment.getReservationId()).orElseThrow(() ->
                new BaseException(BaseResponseStatus.INCORRECT_RESERVATION_DATA)
        );
        if (result.getStatus() != null) {
            PaymentEntity entity = PaymentEntity.builder()
                    .username(payment.getUsername())
                    .ticketPrice(payment.getTicketPrice())
                    .usePoint(payment.getUsePoint())
                    .fee(payment.getFee())
                    .payType(payment.getPayType())
                    .verify(payment.getVerify())
                    .reservationEntity(result)
                    .build();

            entity.createdAt();
            jpaPaymentRepository.save(entity);
        } else {
            throw new BaseException(BaseResponseStatus.ALREADY_PAID_TICKET);
        }
    }


    @Override
    public Payment searchPayment(Long id) throws BaseException {
        PaymentEntity result = jpaPaymentRepository.findByReservationEntity_Id(id);
        Optional<ReservationEntity> reservation = jpaReservationRepository.findById(id);
        Boolean reservationChk = false;
        if ((reservation.get().getPayMethod().equals(result.getPayType()))
                &&(reservation.get().getStatus().equals(ReservationType.IN_PROGRESS))
                &&(reservation.get().getTotalPrice() == (result.getTicketPrice()+result.getFee()- result.getUsePoint()))){
            reservationChk = true;
        }

        if ((result != null)&&(reservationChk)) {
            return Payment.builder()
                    .reservationId(result.getReservationEntity().getId())
                    .username(result.getUsername())
                    .ticketPrice(result.getTicketPrice())
                    .usePoint(result.getUsePoint())
                    .fee(result.getFee())
                    .payType(result.getPayType())
                    .verify(result.getVerify())
                    .createdAt(result.getCreatedAt())
                    .verifiedAt(result.getVerifiedAt())
                    .build();
        } else {
            throw new BaseException(BaseResponseStatus.INCORRECT_RESERVATION_DATA);
        }
    }

    @Override
    public void verify(Payment payment) throws BaseException {
        PaymentEntity searchedEntity = jpaPaymentRepository.findByReservationEntity_Id(payment.getReservationId());
        Optional<ReservationEntity> reservation = jpaReservationRepository.findById(payment.getReservationId());
        if (searchedEntity != null) {
            PaymentEntity entity = PaymentEntity.builder()
                    .id(searchedEntity.getId())
                    .reservationEntity(searchedEntity.getReservationEntity())
                    .username(searchedEntity.getUsername())
                    .ticketPrice(searchedEntity.getTicketPrice())
                    .usePoint(searchedEntity.getUsePoint())
                    .fee(searchedEntity.getFee())
                    .payType(searchedEntity.getPayType())
                    .verify(true)
                    .build();

            entity.verifiedAt();
            jpaPaymentRepository.save(entity);
            if (searchedEntity.getFee() != null) {
                ReservationEntity result = ReservationEntity.builder()
                        .id(reservation.get().getId())
                        .status(ReservationType.EXCHANGED)
                        .build();

                jpaReservationRepository.save(result);
            }
            else {
                ReservationEntity result = ReservationEntity.builder()
                        .id(reservation.get().getId())
                        .programEntity(reservation.get().getProgramEntity())
                        .seatEntity(reservation.get().getSeatEntity())
                        .timesEntity(reservation.get().getTimesEntity())
                        .user(reservation.orElseThrow().getUser())
                        .payMethod(reservation.get().getPayMethod())
                        .ticketNumber(reservation.get().getTicketNumber())
                        .status(ReservationType.COMPLETED)
                        .requestLimit(reservation.get().getRequestLimit())
                        .totalPrice(reservation.get().getTotalPrice())


                        .build();

                jpaReservationRepository.save(result);
            }
        }
        else {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_PAYMENT_DATA);
        }


    }
}

