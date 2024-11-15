package com.gamja.tiggle.like.adapter.out.persistence;

import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramEntity;
import com.gamja.tiggle.user.adapter.out.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, Long> {
    boolean existsByUserEntityAndProgramEntity(UserEntity userEntity, ProgramEntity programEntity);

    void deleteByUserEntityAndProgramEntity(UserEntity userEntity, ProgramEntity programEntity);


    @Query("SELECT l FROM LikeEntity l " +
            "JOIN FETCH l.programEntity p " +
            "JOIN FETCH p.locationEntity loc " +
            "WHERE l.userEntity.id = :userId")
    List<LikeEntity> findLikesWithProgramAndLocationByUserId(@Param("userId") Long userId);
}
