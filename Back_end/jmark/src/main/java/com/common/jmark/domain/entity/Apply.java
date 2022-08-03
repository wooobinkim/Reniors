package com.common.jmark.domain.entity;

import com.common.jmark.domain.entity.Enum.JobOpeningProcess;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.ApplyDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apply {
    @Id
    @GeneratedValue
    @Column(name = "apply_id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private JobOpeningProcess jobOpeningProcess;

    private Date interviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_opening_id")
    JobOpening jobOpening;

    public Apply(ApplyDto applyDto, User user, JobOpening jobOpening) {
        this.id = applyDto.getId();
        this.jobOpeningProcess = applyDto.getJobOpeningProcess();
        this.interviewDate = applyDto.getInterviewDate();
        this.user = user;
        this.jobOpening = jobOpening;
    }

    public void update(ApplyDto applyDto, User user, JobOpening jobOpening) {
        this.jobOpeningProcess = applyDto.getJobOpeningProcess();
        this.interviewDate = applyDto.getInterviewDate();
        this.user = user;
        this.jobOpening = jobOpening;
    }
}
