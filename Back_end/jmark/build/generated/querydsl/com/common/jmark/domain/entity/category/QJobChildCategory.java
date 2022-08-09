package com.common.jmark.domain.entity.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJobChildCategory is a Querydsl query type for JobChildCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJobChildCategory extends EntityPathBase<JobChildCategory> {

    private static final long serialVersionUID = -810980097L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJobChildCategory jobChildCategory = new QJobChildCategory("jobChildCategory");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QJobParentCategory parent;

    public final ListPath<com.common.jmark.domain.entity.recommend.RecommendCondition, com.common.jmark.domain.entity.recommend.QRecommendCondition> recommendConditions = this.<com.common.jmark.domain.entity.recommend.RecommendCondition, com.common.jmark.domain.entity.recommend.QRecommendCondition>createList("recommendConditions", com.common.jmark.domain.entity.recommend.RecommendCondition.class, com.common.jmark.domain.entity.recommend.QRecommendCondition.class, PathInits.DIRECT2);

    public final ListPath<com.common.jmark.domain.entity.SearchCondition, com.common.jmark.domain.entity.QSearchCondition> searchConditions = this.<com.common.jmark.domain.entity.SearchCondition, com.common.jmark.domain.entity.QSearchCondition>createList("searchConditions", com.common.jmark.domain.entity.SearchCondition.class, com.common.jmark.domain.entity.QSearchCondition.class, PathInits.DIRECT2);

    public QJobChildCategory(String variable) {
        this(JobChildCategory.class, forVariable(variable), INITS);
    }

    public QJobChildCategory(Path<? extends JobChildCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJobChildCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJobChildCategory(PathMetadata metadata, PathInits inits) {
        this(JobChildCategory.class, metadata, inits);
    }

    public QJobChildCategory(Class<? extends JobChildCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QJobParentCategory(forProperty("parent")) : null;
    }

}

