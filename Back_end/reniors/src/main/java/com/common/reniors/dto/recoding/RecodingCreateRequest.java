package com.common.reniors.dto.recoding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecodingCreateRequest {
    @NotBlank
    String originalName;
}
