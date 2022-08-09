package com.common.jmark.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompany is a Querydsl query type for Company
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCompany extends EntityPathBase<Company> {

    private static final long serialVersionUID = 1346439503L;

    public static final QCompany company = new QCompany("company");

    public final StringPath address = createString("address");

    public final StringPath companyAppId = createString("companyAppId");

    public final StringPath companyAppPwd = createString("companyAppPwd");

    public final StringPath companyImgName = createString("companyImgName");

    public final StringPath companyImgPath = createString("companyImgPath");

    public final StringPath companyNum = createString("companyNum");

    public final StringPath companyPhone = createString("companyPhone");

    public final StringPath companyUrl = createString("companyUrl");

    public final StringPath establishedAt = createString("establishedAt");

    public final ListPath<Eval, QEval> evals = this.<Eval, QEval>createList("evals", Eval.class, QEval.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<JobOpening, QJobOpening> jobOpenings = this.<JobOpening, QJobOpening>createList("jobOpenings", JobOpening.class, QJobOpening.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath representativePhone = createString("representativePhone");

    public final EnumPath<com.common.jmark.domain.entity.Enum.TypeCompany> typeCompany = createEnum("typeCompany", com.common.jmark.domain.entity.Enum.TypeCompany.class);

    public QCompany(String variable) {
        super(Company.class, forVariable(variable));
    }

    public QCompany(Path<? extends Company> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompany(PathMetadata metadata) {
        super(Company.class, metadata);
    }

}

