package com.common.jmark.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJobOpening is a Querydsl query type for JobOpening
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJobOpening extends EntityPathBase<JobOpening> {

    private static final long serialVersionUID = -1088930615L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJobOpening jobOpening = new QJobOpening("jobOpening");

    public final ListPath<Apply, QApply> applies = this.<Apply, QApply>createList("applies", Apply.class, QApply.class, PathInits.DIRECT2);

    public final QCompany company;

    public final StringPath contents = createString("contents");

    public final StringPath contentsImgName = createString("contentsImgName");

    public final StringPath contentsImgPath = createString("contentsImgPath");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> finishedDate = createDateTime("finishedDate", java.util.Date.class);

    public final com.common.jmark.domain.entity.category.QGugun gugun;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.common.jmark.domain.entity.category.QJobChildCategory jobChildCategory;

    public final StringPath jobPosition = createString("jobPosition");

    public final EnumPath<com.common.jmark.domain.entity.Enum.LastEdu> lastEdu = createEnum("lastEdu", com.common.jmark.domain.entity.Enum.LastEdu.class);

    public final NumberPath<Integer> minCareer = createNumber("minCareer", Integer.class);

    public final NumberPath<Integer> minSalary = createNumber("minSalary", Integer.class);

    public final NumberPath<Integer> numberPeople = createNumber("numberPeople", Integer.class);

    public final StringPath title = createString("title");

    public final EnumPath<com.common.jmark.domain.entity.Enum.TypeEmployment> typeEmployment = createEnum("typeEmployment", com.common.jmark.domain.entity.Enum.TypeEmployment.class);

    public final NumberPath<Integer> workingDay = createNumber("workingDay", Integer.class);

    public QJobOpening(String variable) {
        this(JobOpening.class, forVariable(variable), INITS);
    }

    public QJobOpening(Path<? extends JobOpening> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJobOpening(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJobOpening(PathMetadata metadata, PathInits inits) {
        this(JobOpening.class, metadata, inits);
    }

    public QJobOpening(Class<? extends JobOpening> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new QCompany(forProperty("company")) : null;
        this.gugun = inits.isInitialized("gugun") ? new com.common.jmark.domain.entity.category.QGugun(forProperty("gugun"), inits.get("gugun")) : null;
        this.jobChildCategory = inits.isInitialized("jobChildCategory") ? new com.common.jmark.domain.entity.category.QJobChildCategory(forProperty("jobChildCategory"), inits.get("jobChildCategory")) : null;
    }

}

