package com.gamja.tiggle.program.adapter.in.web.response;

import com.gamja.tiggle.program.domain.Program;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReadLocationResponse {
    private Long id;
    private String locationName;
    private String addressName;
    private List<String> programNames;
}
