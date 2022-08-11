package com.common.jmark.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEval is a Querydsl query type for UserEval
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEval extends EntityPathBase<UserEval> {

    private static final long serialVersionUID = -877199243L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserEval userEval = new QUserEval("userEval");

    public final QEvalQuestion evalQuestion;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final StringPath score = createString("score");

    public final com.common.jmark.domain.entity.user.QUser user;

    public QUserEval(String variable) {
        this(UserEval.class, forVariable(variable), INITS);
    }

    public QUserEval(Path<? extends UserEval> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserEval(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserEval(PathMetadata metadata, PathInits inits) {
        this(UserEval.class, metadata, inits);
    }

    public QUserEval(Class<? extends UserEval> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.evalQuestion = inits.isInitialized("evalQuestion") ? new QEvalQuestion(forProperty("evalQuestion"), inits.get("evalQuestion")) : null;
        this.user = inits.isInitialized("user") ? new com.common.jmark.domain.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

