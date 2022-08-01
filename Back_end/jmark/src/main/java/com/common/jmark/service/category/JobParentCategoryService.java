package com.common.jmark.service.category;

import com.common.jmark.common.exception.DuplicateException;
import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.category.JobParentCategory;
import com.common.jmark.domain.repository.category.JobParentCategoryRepository;
import com.common.jmark.dto.category.JobParentCategoryCreateRequest;
import com.common.jmark.dto.category.JobParentCategoryResponse;
import com.common.jmark.dto.category.JobParentCategoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.jmark.common.exception.NotFoundException.CATEGORY_NOT_FOUND;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobParentCategoryService {

    private final JobParentCategoryRepository jobParentCategoryRepository;

    public Long create(JobParentCategoryCreateRequest request) {
        if(jobParentCategoryRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리입니다.",request.getName()));
        }
        JobParentCategory jpc = JobParentCategory.create(request.getName());
        return jobParentCategoryRepository.save(jpc).getId();
    }

    public void update(Long jpcId, JobParentCategoryUpdateRequest request) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(jpcId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        if(jobParentCategoryRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리입니다.",request.getName()));
        }
        jpc.update(request.getName());
    }

    public void delete(Long jpcId) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(jpcId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        jobParentCategoryRepository.delete(jpc);
    }

    public List<JobParentCategoryResponse> getParentList() {
        List<JobParentCategoryResponse> parents = jobParentCategoryRepository.findAll().stream()
                .map(JobParentCategoryResponse::response)
                .collect(Collectors.toList());
        return parents;
    }
}
