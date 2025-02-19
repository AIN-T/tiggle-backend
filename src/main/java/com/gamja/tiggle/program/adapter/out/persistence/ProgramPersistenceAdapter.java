package com.gamja.tiggle.program.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.like.adapter.out.persistence.JpaLikeRepository;
import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramImageEntity;
import com.gamja.tiggle.program.application.port.in.ReadProgramCommand;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.program.adapter.out.persistence.Entity.CategoryEntity;
import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramEntity;
import com.gamja.tiggle.program.application.port.out.CreateProgramPort;
import com.gamja.tiggle.program.application.port.out.ProgramPort;
import com.gamja.tiggle.program.application.port.out.ReadProgramPort;
import com.gamja.tiggle.program.domain.Program;

import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SectionEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.SectionRepository;
import com.gamja.tiggle.user.adapter.out.persistence.UserEntity;
import com.gamja.tiggle.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProgramPersistenceAdapter implements CreateProgramPort, ReadProgramPort, ProgramPort {
    private final JpaProgramRepository jpaProgramRepository;
    private final JpaProgramImageRepository jpaProgramImageRepository;
    private final JpaCategoryRepository jpaCategoryRepository;
    private final JpaLikeRepository jpaLikeRepository;
    private final SectionRepository sectionRepository;

    @Override
    public void createProgram(Program program) throws BaseException {

        CategoryEntity result = jpaCategoryRepository.findById(program.getCategoryId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.FAIL));

        ProgramEntity entity = ProgramEntity.builder()
                .categoryEntity(result)
                .reservationOpenDate(program.getReservationOpenDate())
                .programName(program.getProgramName())
                .programInfo(program.getProgramInfo())
                .programStartDate(program.getProgramStartDate())
                .programEndDate(program.getProgramEndDate())
                .build();
        jpaProgramRepository.save(entity);


        for (String uploadFilePath : program.getImageUrls()) {
            ProgramImageEntity programImageEntity = ProgramImageEntity.builder()
                    .imgUrl(uploadFilePath)
                    .programEntity(entity)
                    .build();
            jpaProgramImageRepository.save(programImageEntity);
        }

    }


    @Override
    public List<Program> readProgramAll(Program program, ReadProgramCommand command) throws BaseException {
        Pageable pageable = PageRequest.of(command.getPage(), command.getSize(), Sort.by(Sort.Direction.DESC, "reservationOpenDate"));

        Page<ProgramEntity> result = jpaProgramRepository.findAllByCategoryEntity(program.getCategoryId(), pageable);

        List<Program> programs = result.getContent().stream().map(
                (p) -> Program.builder()
                        .id(p.getId())
                        .categoryId(p.getCategoryEntity().getId())
                        .programName(p.getProgramName())
                        .programInfo(p.getProgramInfo())
                        .reservationOpenDate(p.getReservationOpenDate())
                        .programStartDate(p.getProgramStartDate())
                        .programEndDate(p.getProgramEndDate())
                        .imageUrls(p.getProgramImageEntities().stream()
                                .map(programImageEntity -> programImageEntity.getImgUrl())
                                .toList())
                        .locationName(p.getLocationEntity().getLocationName())
                        .build()
        ).collect(Collectors.toUnmodifiableList());

        return programs;
    }

    @Override
    public List<Program> readRealTimeAll(LocalDateTime currentDateTime) throws BaseException {
        List<ProgramEntity> programEntities = jpaProgramRepository.findAll();
        List<Program> programs = programEntities.stream()
                // p의 값이 현재 시간보다 after 인 경우
                .filter(p -> p.getReservationOpenDate().isAfter(currentDateTime))
                // 오름차순 반환 : 리스트 내에 있는 값 두 개씩 비교해서 출력
                .sorted((p1, p2) -> p1.getReservationOpenDate().compareTo(p2.getReservationOpenDate()))
                .map(p -> Program.builder()
                        .categoryId(p.getCategoryEntity().getId())
                        .programName(p.getProgramName())
                        .programInfo(p.getProgramInfo())
                        .programStartDate(p.getProgramStartDate())
                        .programEndDate(p.getProgramEndDate())
                        .reservationOpenDate(p.getReservationOpenDate())
                        .imageUrls(p.getProgramImageEntities().stream().map(programImageEntity -> programImageEntity.getImgUrl()).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toUnmodifiableList());

        return programs;
    }

    @Override
    public List<Program> readRealTimeAllPaged(ReadProgramCommand command) throws BaseException {
        int page = command.getPage();
        int size = command.getSize();
        // 현재 시간을 기준으로 예매 끝난 공연은 나오지 않게 하는 filter
//        LocalDateTime currentDateTime = LocalDateTime.now();

        Pageable pageable = PageRequest.of(page, size);
        Page<ProgramEntity> programEntityPage = jpaProgramRepository.findAll(pageable);

        List<Program> programs = programEntityPage.stream()
//                .filter(p -> p.getReservationOpenDate().isAfter(currentDateTime))
                .sorted(Comparator.comparing(ProgramEntity::getProgramEndDate)) // 예약 오픈 날짜 기준 정렬
                .map(p -> Program.builder()
                        .id(p.getId())
                        .categoryId(p.getCategoryEntity().getId())
                        .programName(p.getProgramName())
                        .programInfo(p.getProgramInfo())
                        .programStartDate(p.getProgramStartDate())
                        .programEndDate(p.getProgramEndDate())
                        .reservationOpenDate(p.getReservationOpenDate())
                        .locationName(p.getLocationEntity().getLocationName())
                        .imageUrls(p.getProgramImageEntities().stream()
                                .map(programImageEntity -> programImageEntity.getImgUrl())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toUnmodifiableList());

        return programs;
    }

    @Override
    public Program getProgramDetail(Long id, User user) throws BaseException {
        ProgramEntity programEntity = jpaProgramRepository.findById(id).orElseThrow(() ->
                new BaseException(BaseResponseStatus.NOT_FOUND_PROGRAM));

        boolean isLike = false;

        if (user != null) {
            isLike = jpaLikeRepository.existsByUserEntityAndProgramEntity(UserEntity.builder().id(user.getId()).build(), ProgramEntity.builder().id(id).build());
        }

        List<String> imgList = new ArrayList<>();
        for (ProgramImageEntity p : programEntity.getProgramImageEntities()) {
            imgList.add(p.getImgUrl());
        }

        Pageable pageable = PageRequest.of(0, 1);
        Page<SectionEntity> sections = sectionRepository.findFirstSectionByProgramIdAndLocationId(programEntity.getLocationEntity().getId(), pageable);

        Long firstSectionId = sections.isEmpty() ? null : sections.getContent().get(0).getId();

        return Program.builder()
                .programName(programEntity.getProgramName())
                .age(programEntity.getAge())
                .locationId(programEntity.getLocationEntity().getId())
                .locationName(programEntity.getLocationEntity().getLocationName())
                .reservationOpenDate(programEntity.getReservationOpenDate())
                .programStartDate(programEntity.getProgramStartDate())
                .programEndDate(programEntity.getProgramEndDate())
                .locationName(programEntity.getLocationEntity().getLocationName())
                .runtime(programEntity.getRuntime())
                .imageUrls(imgList)
                .programInfo(programEntity.getProgramInfo())
                .isLike(isLike)
                .firstSectionId(firstSectionId)
                .build();
    }

    @Override
    public List<Program> searchProgram(ReadProgramCommand command) throws BaseException {
        Pageable pageable = PageRequest.of(command.getPage(), command.getSize(), Sort.by(Sort.Direction.DESC, "reservationOpenDate"));

        Page<ProgramEntity> result = jpaProgramRepository.findByKeyword(command.getKeyword(), pageable);

        return result.getContent().stream().map(
                (p) -> Program.builder()
                        .id(p.getId())
                        .categoryId(p.getCategoryEntity().getId())
                        .programName(p.getProgramName())
                        .programInfo(p.getProgramInfo())
                        .reservationOpenDate(p.getReservationOpenDate())
                        .programStartDate(p.getProgramStartDate())
                        .programEndDate(p.getProgramEndDate())
                        .imageUrls(p.getProgramImageEntities().stream()
                                .map(ProgramImageEntity::getImgUrl)
                                .collect(Collectors.toList()))
                        .locationName(p.getLocationEntity().getLocationName())
                        .build()
        ).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean existProgram(Long id) throws BaseException {
        if (!jpaProgramRepository.existsById(id)) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_PROGRAM);
        }
        return true;
    }

    @Override
    public Long getLocationId(Long programId) throws BaseException {
        Long locationIdById = jpaProgramRepository.findLocationIdById(programId);
        if (locationIdById == null) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_LOCATION_ID);
        }
        return locationIdById;
    }

    @Override
    public void findByProgramIdAndLocationId(Long programId, Long locationId) throws BaseException {
        if (!jpaProgramRepository.existsProgramEntityByIdAndLocationEntityId(programId, locationId)) {
            throw new BaseException(BaseResponseStatus.PROGRAM_NOT_IN_LOCATION);
        }
    }

}
