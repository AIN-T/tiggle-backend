package com.gamja.tiggle.section.domain;

import lombok.Builder;
import lombok.Getter;
import com.gamja.tiggle.grade.domain.Grade;
import com.gamja.tiggle.location.domain.Location;

@Getter
@Builder
public class Section {

    private Long id;
    private Location location;
    private Grade grade;
    private String section;
    private int seatNumber;
}
