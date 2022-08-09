package com.common.jmark.domain.entity.recommend;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecommendCondition is a Querydsl query type for RecommendCondition
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecommendCondition extends EntityPathBase<RecommendCondition> {

    private static final long serialVersionUID = -245174113L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecommendCondition recommendCondition = new QRecommendCondition("recommendCondition");

    public final com.common.jmark.domain.entity.category.QGugun gugun;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.common.jmark.domain.entity.category.QJobChildCategory jobChildCategory;

    public final NumberPath<Integer> minSalary = createNumber("minSalary", Integer.class);

    public final com.common.jmark.domain.entity.user.QUser user;

    public final NumberPath<Integer> workingDay = createNumber("workingDay", Integer.class);

    public QRecommendCondition(String variable) {
        this(RecommendCondition.class, forVariable(variable), INITS);
    }

    public QRecommendCondition(Path<? extends RecommendCondition> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecommendCondition(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecommendCondition(PathMetadata metadata, PathInits inits) {
        this(RecommendCondition.class, metadata, inits);
    }

    public QRecommendCondition(Class<? extends RecommendCondition> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gugun = inits.isInitialized("gugun") ? new com.common.jmark.domain.entity.category.QGugun(forProperty("gugun"), inits.get("gugun")) : null;
        this.jobChildCategory = inits.isInitialized("jobChildCategory") ? new com.common.jmark.domain.entity.category.QJobChildCategory(forProperty("jobChildCategory"), inits.get("jobChildCategory")) : null;
        this.user = inits.isInitialized("user") ? new com.common.jmark.domain.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

