package com.gamja.tiggle.program.application.port.in;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReadProgramCommand {

    private Long categoryId;
    private LocalDateTime currentDateTime;

}
