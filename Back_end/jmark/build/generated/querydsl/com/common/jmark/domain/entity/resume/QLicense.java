package com.common.jmark.domain.entity.resume;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLicense is a Querydsl query type for License
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLicense extends EntityPathBase<License> {

    private static final long serialVersionUID = -2044383698L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLicense license = new QLicense("license");

    public final StringPath grade = createString("grade");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final DateTimePath<java.util.Date> passedAt = createDateTime("passedAt", java.util.Date.class);

    public final com.common.jmark.domain.entity.user.QUser user;

    public QLicense(String variable) {
        this(License.class, forVariable(variable), INITS);
    }

    public QLicense(Path<? extends License> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLicense(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLicense(PathMetadata metadata, PathInits inits) {
        this(License.class, metadata, inits);
    }

    public QLicense(Class<? extends License> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.common.jmark.domain.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

