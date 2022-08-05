package com.common.jmark.dto.board;

import com.common.jmark.domain.entity.board.Board;
import com.common.jmark.domain.entity.board.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    @NotBlank
    Long commnetId;

    @NotBlank
    String contents;

    @NotBlank
    Long userid;

    @NotBlank
    String userName;

    @NotNull LocalDateTime updatedAt;

    public static CommentResponse response(Comment comment){
        return new CommentResponse(comment.getId(), comment.getContents(), comment.getUser().getId(), comment.getUser().getName(), comment.getUpdatedAt());
    }
}
