package com.common.jmark.domain.entity.board;

import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.domain.entity.category.JobParentCategory;
import com.common.jmark.domain.entity.category.Sido;
import com.common.jmark.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String contents;

    private int views;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_parent_category_id")
    private JobParentCategory category;

    public static Board create(String title, String contents, User user, JobParentCategory category){
        Board board = new Board();
        board.title = title;
        board.contents = contents;
        board.user = user;
        board.category = category;
        return board;
    }

    public void update(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

}
