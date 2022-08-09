package com.common.jmark.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEval is a Querydsl query type for Eval
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEval extends EntityPathBase<Eval> {

    private static final long serialVersionUID = 716924170L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEval eval = new QEval("eval");

    public final QCompany company;

    public final ListPath<EvalQuestion, QEvalQuestion> evalQuestions = this.<EvalQuestion, QEvalQuestion>createList("evalQuestions", EvalQuestion.class, QEvalQuestion.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QEval(String variable) {
        this(Eval.class, forVariable(variable), INITS);
    }

    public QEval(Path<? extends Eval> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEval(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEval(PathMetadata metadata, PathInits inits) {
        this(Eval.class, metadata, inits);
    }

    public QEval(Class<? extends Eval> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new QCompany(forProperty("company")) : null;
    }

}

