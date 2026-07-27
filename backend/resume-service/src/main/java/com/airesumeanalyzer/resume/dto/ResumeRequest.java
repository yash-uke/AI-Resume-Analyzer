package com.airesumeanalyzer.resume.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeRequest {

    @NotNull
    private Long userId;

    @NotBlank
    private String fileName;

    @NotBlank
    private String fileType;

    @NotBlank
    private String fileUrl;

}