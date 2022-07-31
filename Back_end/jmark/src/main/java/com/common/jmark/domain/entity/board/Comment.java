package com.common.jmark.domain.entity.board;

import com.common.jmark.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String contents;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_commnet")
    private User user;

    public static Comment create(String title, String contents, Board board, User user){
        Comment comment = new Comment();
        comment.contents = contents;
        comment.board = board;
        comment.user = user;
        return comment;
    }

    public void update(String contents){
        this.contents = contents;
    }

}
