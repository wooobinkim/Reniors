package com.common.jmark.dto;

import com.common.jmark.domain.entity.HopeJob;
import com.common.jmark.dto.category.JobChildCategoryResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class HopeJobDto {
    private Long id;
    private Long jobChildCategoryId;
    private JobChildCategoryResponse jobChildCategoryResponse;

    public HopeJobDto(HopeJob hopeJob, JobChildCategoryResponse jobChildCategoryResponse) {
        this.id = hopeJob.getId();
        this.jobChildCategoryResponse = jobChildCategoryResponse;
    }
}
