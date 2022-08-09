package com.common.jmark.domain.entity.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSido is a Querydsl query type for Sido
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSido extends EntityPathBase<Sido> {

    private static final long serialVersionUID = -21007389L;

    public static final QSido sido = new QSido("sido");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final ListPath<Gugun, QGugun> guguns = this.<Gugun, QGugun>createList("guguns", Gugun.class, QGugun.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QSido(String variable) {
        super(Sido.class, forVariable(variable));
    }

    public QSido(Path<? extends Sido> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSido(PathMetadata metadata) {
        super(Sido.class, metadata);
    }

}

