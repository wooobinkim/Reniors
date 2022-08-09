package com.common.jmark.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSearchCondition is a Querydsl query type for SearchCondition
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSearchCondition extends EntityPathBase<SearchCondition> {

    private static final long serialVersionUID = -194650331L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSearchCondition searchCondition = new QSearchCondition("searchCondition");

    public final ListPath<HopeArea, QHopeArea> hopeAreas = this.<HopeArea, QHopeArea>createList("hopeAreas", HopeArea.class, QHopeArea.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.common.jmark.domain.entity.category.QJobChildCategory jobChildCategory;

    public final EnumPath<com.common.jmark.domain.entity.Enum.LastEdu> lastEdu = createEnum("lastEdu", com.common.jmark.domain.entity.Enum.LastEdu.class);

    public final NumberPath<Integer> minCareer = createNumber("minCareer", Integer.class);

    public final NumberPath<Integer> minSalary = createNumber("minSalary", Integer.class);

    public final StringPath name = createString("name");

    public final EnumPath<com.common.jmark.domain.entity.Enum.TypeEmployment> typeEmployment = createEnum("typeEmployment", com.common.jmark.domain.entity.Enum.TypeEmployment.class);

    public final com.common.jmark.domain.entity.user.QUser user;

    public final NumberPath<Integer> workingDay = createNumber("workingDay", Integer.class);

    public QSearchCondition(String variable) {
        this(SearchCondition.class, forVariable(variable), INITS);
    }

    public QSearchCondition(Path<? extends SearchCondition> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSearchCondition(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSearchCondition(PathMetadata metadata, PathInits inits) {
        this(SearchCondition.class, metadata, inits);
    }

    public QSearchCondition(Class<? extends SearchCondition> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.jobChildCategory = inits.isInitialized("jobChildCategory") ? new com.common.jmark.domain.entity.category.QJobChildCategory(forProperty("jobChildCategory"), inits.get("jobChildCategory")) : null;
        this.user = inits.isInitialized("user") ? new com.common.jmark.domain.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

