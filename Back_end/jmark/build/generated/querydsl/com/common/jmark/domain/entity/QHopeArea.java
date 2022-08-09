package com.common.jmark.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHopeArea is a Querydsl query type for HopeArea
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHopeArea extends EntityPathBase<HopeArea> {

    private static final long serialVersionUID = -1011152553L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHopeArea hopeArea = new QHopeArea("hopeArea");

    public final com.common.jmark.domain.entity.category.QGugun gugun;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSearchCondition searchCondition;

    public QHopeArea(String variable) {
        this(HopeArea.class, forVariable(variable), INITS);
    }

    public QHopeArea(Path<? extends HopeArea> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHopeArea(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHopeArea(PathMetadata metadata, PathInits inits) {
        this(HopeArea.class, metadata, inits);
    }

    public QHopeArea(Class<? extends HopeArea> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gugun = inits.isInitialized("gugun") ? new com.common.jmark.domain.entity.category.QGugun(forProperty("gugun"), inits.get("gugun")) : null;
        this.searchCondition = inits.isInitialized("searchCondition") ? new QSearchCondition(forProperty("searchCondition"), inits.get("searchCondition")) : null;
    }

}

