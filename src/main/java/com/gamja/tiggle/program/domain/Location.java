package com.gamja.tiggle.program.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Location {

    @Id
    private Long id;
    private String locationName;
    private String addressName;
    private List<String> programNames;
//    private String region_1depth_name;
//    private String region_2depth_name;
//    private String region_3depth_name;
//    private String region_4depth_h_name;
//    private int x;
//    private int y;
    private String seatImg;
    private String thumbnail;
}
