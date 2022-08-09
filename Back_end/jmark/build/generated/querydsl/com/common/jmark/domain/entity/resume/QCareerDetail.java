package com.common.jmark.domain.entity.resume;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCareerDetail is a Querydsl query type for CareerDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCareerDetail extends EntityPathBase<CareerDetail> {

    private static final long serialVersionUID = -1410041950L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCareerDetail careerDetail = new QCareerDetail("careerDetail");

    public final StringPath companyName = createString("companyName");

    public final DateTimePath<java.util.Date> finishedAt = createDateTime("finishedAt", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath jobContents = createString("jobContents");

    public final DateTimePath<java.util.Date> startedAt = createDateTime("startedAt", java.util.Date.class);

    public final com.common.jmark.domain.entity.user.QUser user;

    public QCareerDetail(String variable) {
        this(CareerDetail.class, forVariable(variable), INITS);
    }

    public QCareerDetail(Path<? extends CareerDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCareerDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCareerDetail(PathMetadata metadata, PathInits inits) {
        this(CareerDetail.class, metadata, inits);
    }

    public QCareerDetail(Class<? extends CareerDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.common.jmark.domain.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

