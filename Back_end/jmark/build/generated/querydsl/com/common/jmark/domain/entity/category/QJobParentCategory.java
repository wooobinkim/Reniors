package com.common.jmark.domain.entity.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJobParentCategory is a Querydsl query type for JobParentCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJobParentCategory extends EntityPathBase<JobParentCategory> {

    private static final long serialVersionUID = 925304227L;

    public static final QJobParentCategory jobParentCategory = new QJobParentCategory("jobParentCategory");

    public final ListPath<com.common.jmark.domain.entity.board.Board, com.common.jmark.domain.entity.board.QBoard> boards = this.<com.common.jmark.domain.entity.board.Board, com.common.jmark.domain.entity.board.QBoard>createList("boards", com.common.jmark.domain.entity.board.Board.class, com.common.jmark.domain.entity.board.QBoard.class, PathInits.DIRECT2);

    public final ListPath<JobChildCategory, QJobChildCategory> childs = this.<JobChildCategory, QJobChildCategory>createList("childs", JobChildCategory.class, QJobChildCategory.class, PathInits.DIRECT2);

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QJobParentCategory(String variable) {
        super(JobParentCategory.class, forVariable(variable));
    }

    public QJobParentCategory(Path<? extends JobParentCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJobParentCategory(PathMetadata metadata) {
        super(JobParentCategory.class, metadata);
    }

}

