package com.gamja.tiggle.program.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCategoryCommand {
    private String categoryName;
}
