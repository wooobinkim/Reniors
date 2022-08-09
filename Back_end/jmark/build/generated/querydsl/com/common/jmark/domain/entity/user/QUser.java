package com.common.jmark.domain.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 845419680L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final StringPath address = createString("address");

    public final ListPath<com.common.jmark.domain.entity.InterviewQuestion.Answer, com.common.jmark.domain.entity.InterviewQuestion.QAnswer> answers = this.<com.common.jmark.domain.entity.InterviewQuestion.Answer, com.common.jmark.domain.entity.InterviewQuestion.QAnswer>createList("answers", com.common.jmark.domain.entity.InterviewQuestion.Answer.class, com.common.jmark.domain.entity.InterviewQuestion.QAnswer.class, PathInits.DIRECT2);

    public final ListPath<com.common.jmark.domain.entity.Apply, com.common.jmark.domain.entity.QApply> applies = this.<com.common.jmark.domain.entity.Apply, com.common.jmark.domain.entity.QApply>createList("applies", com.common.jmark.domain.entity.Apply.class, com.common.jmark.domain.entity.QApply.class, PathInits.DIRECT2);

    public final ListPath<com.common.jmark.domain.entity.resume.Award, com.common.jmark.domain.entity.resume.QAward> awards = this.<com.common.jmark.domain.entity.resume.Award, com.common.jmark.domain.entity.resume.QAward>createList("awards", com.common.jmark.domain.entity.resume.Award.class, com.common.jmark.domain.entity.resume.QAward.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> birth = createDateTime("birth", java.util.Date.class);

    public final ListPath<com.common.jmark.domain.entity.board.Board, com.common.jmark.domain.entity.board.QBoard> boards = this.<com.common.jmark.domain.entity.board.Board, com.common.jmark.domain.entity.board.QBoard>createList("boards", com.common.jmark.domain.entity.board.Board.class, com.common.jmark.domain.entity.board.QBoard.class, PathInits.DIRECT2);

    public final ListPath<com.common.jmark.domain.entity.resume.CareerDetail, com.common.jmark.domain.entity.resume.QCareerDetail> careerDetails = this.<com.common.jmark.domain.entity.resume.CareerDetail, com.common.jmark.domain.entity.resume.QCareerDetail>createList("careerDetails", com.common.jmark.domain.entity.resume.CareerDetail.class, com.common.jmark.domain.entity.resume.QCareerDetail.class, PathInits.DIRECT2);

    public final ListPath<com.common.jmark.domain.entity.board.Comment, com.common.jmark.domain.entity.board.QComment> comments = this.<com.common.jmark.domain.entity.board.Comment, com.common.jmark.domain.entity.board.QComment>createList("comments", com.common.jmark.domain.entity.board.Comment.class, com.common.jmark.domain.entity.board.QComment.class, PathInits.DIRECT2);

    public final EnumPath<com.common.jmark.domain.entity.Type.Gender> gender = createEnum("gender", com.common.jmark.domain.entity.Type.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.common.jmark.domain.entity.Type.IsOpen> isOpen = createEnum("isOpen", com.common.jmark.domain.entity.Type.IsOpen.class);

    public final StringPath kakaoId = createString("kakaoId");

    public final EnumPath<com.common.jmark.domain.entity.Enum.LastEdu> lastEdu = createEnum("lastEdu", com.common.jmark.domain.entity.Enum.LastEdu.class);

    public final ListPath<com.common.jmark.domain.entity.resume.License, com.common.jmark.domain.entity.resume.QLicense> licenses = this.<com.common.jmark.domain.entity.resume.License, com.common.jmark.domain.entity.resume.QLicense>createList("licenses", com.common.jmark.domain.entity.resume.License.class, com.common.jmark.domain.entity.resume.QLicense.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final StringPath portfolioName = createString("portfolioName");

    public final StringPath portfolioPath = createString("portfolioPath");

    public final StringPath profileImgName = createString("profileImgName");

    public final StringPath profileImgPath = createString("profileImgPath");

    public final com.common.jmark.domain.entity.recommend.QRecommendCondition recommendCondition;

    public final EnumPath<com.common.jmark.domain.entity.Type.Role> role = createEnum("role", com.common.jmark.domain.entity.Type.Role.class);

    public final ListPath<com.common.jmark.domain.entity.SearchCondition, com.common.jmark.domain.entity.QSearchCondition> searchConditions = this.<com.common.jmark.domain.entity.SearchCondition, com.common.jmark.domain.entity.QSearchCondition>createList("searchConditions", com.common.jmark.domain.entity.SearchCondition.class, com.common.jmark.domain.entity.QSearchCondition.class, PathInits.DIRECT2);

    public final NumberPath<Integer> totalCareer = createNumber("totalCareer", Integer.class);

    public final StringPath userAppId = createString("userAppId");

    public final StringPath userAppPwd = createString("userAppPwd");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recommendCondition = inits.isInitialized("recommendCondition") ? new com.common.jmark.domain.entity.recommend.QRecommendCondition(forProperty("recommendCondition"), inits.get("recommendCondition")) : null;
    }

}

