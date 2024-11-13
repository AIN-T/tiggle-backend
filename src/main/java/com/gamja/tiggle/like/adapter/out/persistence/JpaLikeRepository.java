package com.gamja.tiggle.like.adapter.out.persistence;

import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramEntity;
import com.gamja.tiggle.user.adapter.out.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, Long> {
    boolean existsByUserEntityAndProgramEntity(UserEntity userEntity, ProgramEntity programEntity);

    void deleteByUserEntityAndProgramEntity(UserEntity userEntity, ProgramEntity programEntity);
}
