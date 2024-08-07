package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.ReservationRepository;
import com.gamja.tiggle.reservation.application.port.out.ReadReservationPort;
import com.gamja.tiggle.reservation.domain.Reservation;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@PersistenceAdapter
@RequiredArgsConstructor
public class ReadReservationPersistenceAdapter implements ReadReservationPort {

    private final ReservationRepository repository;

    @Override
    public ReservationEntity read(Long reservationId) throws BaseException {
        return repository.findById(reservationId).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_RESERVATION));
    }

    @Override
    public Reservation readReservation(Reservation reservation) throws BaseException {
        ReservationEntity result = repository.findReservationWithDetails(reservation.getId());
        List<String> imageFiles = result.getProgramEntity().getProgramImageEntities().stream()
                .map(programImageEntity -> programImageEntity.getImgUrl())
                .collect(Collectors.toList());

        Reservation reservations = Reservation.builder()
                .ticketNumber(result.getTicketNumber())
                .createdAt(result.getCreatedAt())
                .date(result.getTimesEntity().getDate())
                .locationName(result.getProgramEntity().getLocationEntity().getLocationName())
                .name(result.getUser().getName())
                .seatInfo(
                        result.getSeatEntity().getRow()+"구역 " +result.getSeatEntity().getSectionEntity().getColumnCount()+"열 "+result.getSeatEntity().getSeatNumber()+"번")
                .totalPrice(result.getTotalPrice())
                .gradeName(result.getSeatEntity().getSectionEntity().getGradeEntity().getGradeName())
                .status(result.getStatus())
                .programName(result.getProgramEntity().getProgramName())
                .programInfo(result.getProgramEntity().getProgramInfo())
                .imageFiles(imageFiles)
                .payType(result.getPaymentEntity().getPayType())
                .ticketPrice(result.getPaymentEntity().getTicketPrice())
                .usePoint(result.getPaymentEntity().getUsePoint())
                .build();
        return reservations;
    }

    @Override
    public List<Reservation> myRead(User user) throws BaseException {
        List<ReservationEntity> result = repository.findReservationsByUserId(user.getId());
        List<Reservation> reservations = result.stream()
                .map(r -> {
                    // 이미지를 2개씩 가지고 오길래 한 개만 가지고 오도록 추가
                    String firstImageUrl = r.getProgramEntity()
                            .getProgramImageEntities()
                            .stream()
                            .map(programImageEntity -> programImageEntity.getImgUrl())
                            .findFirst()
                            .orElse(null); // 또는 기본값을 설정할 수 있습니다.

                    List<String> imageFilesList = firstImageUrl == null ? Collections.emptyList() : Collections.singletonList(firstImageUrl);
                    return Reservation.builder()
                            .id(r.getId())
                            .ticketNumber(r.getTicketNumber())
                            .createdAt(r.getCreatedAt())
                            .date(r.getTimesEntity().getDate())
                            .locationName(r.getProgramEntity().getLocationEntity().getLocationName())
                            .programName(r.getProgramEntity().getProgramName())
                            .programStartDate(r.getProgramEntity().getProgramStartDate())
                            .programEndDate(r.getProgramEntity().getProgramEndDate())
                            .status(r.getStatus())
                            .requestLimit(r.getRequestLimit())
                            .imageFiles(imageFilesList) // 리스트에 단일 이미지 URL만 설정
                            .build();
                })
                .collect(Collectors.toUnmodifiableList());

        // 3. 결과 리스트를 반환합니다.
        return reservations;
    }
}
