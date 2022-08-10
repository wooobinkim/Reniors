package com.common.reniors.dto.recoding;

import com.common.reniors.domain.entity.board.Board;
import com.common.reniors.domain.entity.recoding.Recoding;
import com.common.reniors.dto.board.BoardResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecodingResponse {
    @NotNull
    Long recodingId;

    @NotBlank
    String fileName;

    @NotBlank
    String recodeURL;

    @NotNull LocalDateTime updatedAt;

    public static RecodingResponse response(Recoding recoding){
        return new RecodingResponse(recoding.getId(),recoding.getFileName(),recoding.getRecodeURL(), recoding.getCreatedAt());
    }
}
