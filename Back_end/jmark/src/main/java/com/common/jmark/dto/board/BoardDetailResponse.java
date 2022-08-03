package com.common.jmark.dto.board;

import com.common.jmark.domain.entity.board.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetailResponse {

    @NotNull
    Long boardId;

    @NotBlank
    String title;

    @NotBlank
    String contents;

    @NotBlank
    String userName;

    @NotNull LocalDateTime updatedAt;

    public static BoardDetailResponse response(Board board){
        return new BoardDetailResponse(board.getId(), board.getTitle(), board.getContents(), board.getUser().getName(), board.getUpdatedAt());
    }
}
