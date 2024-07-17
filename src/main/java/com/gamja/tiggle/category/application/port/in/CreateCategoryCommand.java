package com.gamja.tiggle.category.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCategoryCommand {
    private String categoryName;
}
