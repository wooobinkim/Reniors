package com.common.jmark.domain.entity;

import com.common.jmark.domain.entity.Enum.JobOpeningProcess;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.Apply.ApplyUpdateRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "YYYY-MM-DD hh:mm")
    private Date interviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_opening_id")
    JobOpening jobOpening;

    public Apply(User user, JobOpening jobOpening) {
        this.jobOpeningProcess = JobOpeningProcess.서류심사중;
        this.user = user;
        this.jobOpening = jobOpening;
    }

    public void update(ApplyUpdateRequest applyUpdateRequest, User user, JobOpening jobOpening) {
        this.jobOpeningProcess = applyUpdateRequest.getJobOpeningProcess();
        this.interviewDate = applyUpdateRequest.getInterviewDate();
        this.user = user;
        this.jobOpening = jobOpening;
    }
}
