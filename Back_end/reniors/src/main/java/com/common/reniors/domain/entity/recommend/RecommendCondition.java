package com.common.reniors.domain.entity.recommend;

import com.common.reniors.domain.entity.category.Gugun;
import com.common.reniors.domain.entity.category.JobChildCategory;
import com.common.reniors.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RecommendCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommend_condition_id")
    private long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_child_category_id")
    private JobChildCategory jobChildCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gugun_id")
    private Gugun gugun;

    private int workingDay;

    private int minSalary;

    public static RecommendCondition create(User user, JobChildCategory jobChildCategory, Gugun gugun, int workingDay, int minSalary) {
        RecommendCondition recommendCondition = new RecommendCondition();
        recommendCondition.user = user;
        recommendCondition.jobChildCategory = jobChildCategory;
        recommendCondition.gugun = gugun;
        recommendCondition.workingDay = workingDay;
        recommendCondition.minSalary = minSalary;
        return recommendCondition;
    }

    public void update(JobChildCategory jobChildCategory, Gugun gugun, int workingDay, int minSalary) {
        this.jobChildCategory = jobChildCategory;
        this.gugun = gugun;
        this.workingDay = workingDay;
        this.minSalary = minSalary;
    }
}
