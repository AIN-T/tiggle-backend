package com.gamja.tiggle.reservation.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Grade {

    @Id
    private Long id;
    private String gradeName;
}
