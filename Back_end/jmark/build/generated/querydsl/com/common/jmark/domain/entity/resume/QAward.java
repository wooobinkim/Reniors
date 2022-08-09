package com.common.jmark.domain.entity.resume;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAward is a Querydsl query type for Award
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAward extends EntityPathBase<Award> {

    private static final long serialVersionUID = 1355725706L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAward award = new QAward("award");

    public final DateTimePath<java.util.Date> awardedAt = createDateTime("awardedAt", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final com.common.jmark.domain.entity.user.QUser user;

    public QAward(String variable) {
        this(Award.class, forVariable(variable), INITS);
    }

    public QAward(Path<? extends Award> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAward(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAward(PathMetadata metadata, PathInits inits) {
        this(Award.class, metadata, inits);
    }

    public QAward(Class<? extends Award> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.common.jmark.domain.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

