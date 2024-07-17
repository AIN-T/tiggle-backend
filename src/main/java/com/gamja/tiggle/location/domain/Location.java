package com.gamja.tiggle.location.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Location {

    @Id
    private Long locationId;

    private String name;
    private String address_name;
    private String region_1depth_name;
    private String region_2depth_name;
    private String region_3depth_name;
    private String region_4depth_h_name;

    private int x;
    private int y;
    private String seatImg;
    private String Thumbnail;
}
