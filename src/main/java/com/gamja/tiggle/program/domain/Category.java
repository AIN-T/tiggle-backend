package com.gamja.tiggle.program.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Category {
    private Long id;
    private String categoryName;
    private List<String> programs;
}
