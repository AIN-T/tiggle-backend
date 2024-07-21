package com.gamja.tiggle.program.adapter.in.web.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationRequest {
    private String locationName;
    private String addressName;
    private Long programId;
    private String seatImg;
    private String thumbnail;

}
