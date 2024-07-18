package com.gamja.tiggle.reservation.adapter.out.persistence.repositroy;

import com.gamja.tiggle.reservation.adapter.in.web.response.GetRemainedSeatResponse;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

    @Query("SELECT new com.gamja.tiggle.reservation.adapter.in.web.response.GetRemainedSeatResponse(s.id, s.section.id, s.seatNumber) " +
            "FROM SeatEntity s " +
            "LEFT JOIN ReservationEntity r ON s.id = r.seat.id " +
            "AND r.program.id = :programId " +
            "AND r.times.id = :timesId " +
            "WHERE s.section.id = :sectionId " +
            "AND (r.status IS NULL OR r.status = 'AVAILABLE' OR r.status = 'REFUNDED')")
    List<GetRemainedSeatResponse> findAvailableSeat(@Param("programId") Long programId,
                                                    @Param("timesId") Long timesId,
                                                    @Param("sectionId") Long sectionId);
}
