package com.gamja.tiggle.like.application.service;

import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.like.adapter.in.web.response.ReadMyLikeResponse;
import com.gamja.tiggle.like.application.port.in.ReadLikeUseCase;
import com.gamja.tiggle.like.application.port.out.LikePort;
import com.gamja.tiggle.program.domain.Program;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ReadLikeService implements ReadLikeUseCase {
    private final LikePort likePort;

    @Override
    public List<ReadMyLikeResponse> readMyLikes(User user) {
        List<Program> programs = likePort.readMyLikes(user);

        List<ReadMyLikeResponse> responses = programs.stream().map(p -> ReadMyLikeResponse.builder()
                .title(p.getProgramName())
                .id(p.getId())
                .imageUrl(p.getImageUrls().get(0))
                .location(p.getLocationName())
                .date(formatDateRange(p.getProgramStartDate(), p.getProgramEndDate()))
                .build()
        ).toList();

        return responses;
    }


    public static String formatDateRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        String startDate = formatter.format(startDateTime);
        String endDate = formatter.format(endDateTime);

        return startDate + " - " + endDate;
    }

}
