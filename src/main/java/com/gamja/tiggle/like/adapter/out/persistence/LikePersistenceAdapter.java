package com.gamja.tiggle.like.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.like.application.port.in.CreateLikeCommand;
import com.gamja.tiggle.like.application.port.out.LikePort;
import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramEntity;
import com.gamja.tiggle.program.adapter.out.persistence.JpaProgramRepository;
import com.gamja.tiggle.user.adapter.out.persistence.JpaUserRepository;
import com.gamja.tiggle.user.adapter.out.persistence.UserEntity;
import lombok.RequiredArgsConstructor;


@PersistenceAdapter
@RequiredArgsConstructor
public class LikePersistenceAdapter implements LikePort {
    private final JpaLikeRepository likeRepository;
    private final JpaProgramRepository programRepository;
    private final JpaUserRepository userRepository;

    @Override
    public boolean isLikedByUser(CreateLikeCommand command) {
        return likeRepository.existsByUserEntityAndProgramEntity(UserEntity.builder().id(command.getUser().getId()).build(), ProgramEntity.builder().id(command.getProgramId()).build());
    }

    @Override
    public void like(CreateLikeCommand command) throws BaseException {

        ProgramEntity programEntity = programRepository.findById(command.getProgramId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_PROGRAM));

        UserEntity userEntity = userRepository.findById(command.getUser().getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_USER));

        likeRepository.save(LikeEntity.builder()
                .userEntity(userEntity)
                .programEntity(programEntity)
                .build());
    }

    @Override
    public void unlike(CreateLikeCommand command) {
        likeRepository.deleteByUserEntityAndProgramEntity(UserEntity.builder().id(command.getUser().getId()).build(), ProgramEntity.builder().id(command.getProgramId()).build());
    }
}
