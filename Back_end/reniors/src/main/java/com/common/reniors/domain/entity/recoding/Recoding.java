package com.common.reniors.domain.entity.recoding;

import com.common.reniors.domain.entity.board.Board;
import com.common.reniors.domain.entity.board.Comment;
import com.common.reniors.domain.entity.category.JobParentCategory;
import com.common.reniors.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Recoding extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recoding_id")
    private Long id;

    private String fileName;

    @NotNull
    private String recodeURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Recoding create(String fileName, String recodeName, User user){
        Recoding recoding = new Recoding();
        recoding.fileName = fileName;
        recoding.recodeURL = recodeName;
        recoding.user = user;
        return recoding;
    }
}
