package com.common.jmark.dto.board;

import com.common.jmark.domain.entity.board.Board;
import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.dto.category.GugunResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse {

    @NotNull
    Long boardId;

    @NotBlank
    String title;

    @NotBlank
    String userName;

    @NotNull LocalDateTime updatedAt;

    public static BoardResponse response(Board board){
        return new BoardResponse(board.getId(), board.getTitle(), board.getUser().getName(), board.getUpdatedAt());
    }
}
