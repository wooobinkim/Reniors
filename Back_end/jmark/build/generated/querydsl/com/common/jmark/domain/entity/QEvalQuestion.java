package com.common.jmark.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEvalQuestion is a Querydsl query type for EvalQuestion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEvalQuestion extends EntityPathBase<EvalQuestion> {

    private static final long serialVersionUID = -930129136L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEvalQuestion evalQuestion = new QEvalQuestion("evalQuestion");

    public final StringPath contents = createString("contents");

    public final QEval eval;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QEvalQuestion(String variable) {
        this(EvalQuestion.class, forVariable(variable), INITS);
    }

    public QEvalQuestion(Path<? extends EvalQuestion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEvalQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEvalQuestion(PathMetadata metadata, PathInits inits) {
        this(EvalQuestion.class, metadata, inits);
    }

    public QEvalQuestion(Class<? extends EvalQuestion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eval = inits.isInitialized("eval") ? new QEval(forProperty("eval"), inits.get("eval")) : null;
    }

}

