package com.common.jmark.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApply is a Querydsl query type for Apply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApply extends EntityPathBase<Apply> {

    private static final long serialVersionUID = 745954496L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApply apply = new QApply("apply");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> interviewDate = createDateTime("interviewDate", java.util.Date.class);

    public final QJobOpening jobOpening;

    public final EnumPath<com.common.jmark.domain.entity.Enum.JobOpeningProcess> jobOpeningProcess = createEnum("jobOpeningProcess", com.common.jmark.domain.entity.Enum.JobOpeningProcess.class);

    public final com.common.jmark.domain.entity.user.QUser user;

    public QApply(String variable) {
        this(Apply.class, forVariable(variable), INITS);
    }

    public QApply(Path<? extends Apply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApply(PathMetadata metadata, PathInits inits) {
        this(Apply.class, metadata, inits);
    }

    public QApply(Class<? extends Apply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.jobOpening = inits.isInitialized("jobOpening") ? new QJobOpening(forProperty("jobOpening"), inits.get("jobOpening")) : null;
        this.user = inits.isInitialized("user") ? new com.common.jmark.domain.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

