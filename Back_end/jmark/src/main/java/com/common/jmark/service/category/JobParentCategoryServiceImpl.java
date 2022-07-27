package com.common.jmark.service.category;

import com.common.exception.DuplicateException;
import com.common.exception.NotFoundException;
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


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobParentCategoryServiceImpl implements JobParentCategoryService{

    private final JobParentCategoryRepository jobParentCategoryRepository;

    @Override
    public Long create(JobParentCategoryCreateRequest request) {
        if(jobParentCategoryRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리입니다.",request.getName()));
        }
        JobParentCategory jpc = JobParentCategory.create(request.getName());
        return jobParentCategoryRepository.save(jpc).getId();
    }

    @Override
    public void update(Long jpcId, JobParentCategoryUpdateRequest request) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(jpcId)
                .orElseThrow(()->new NotFoundException("Not Found Parent"));
        if(jobParentCategoryRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리입니다.",request.getName()));
        }
        jpc.update(request.getName());
    }

    @Override
    public void delete(Long jpcId) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(jpcId)
                .orElseThrow(()->new NotFoundException("Not Found Parent"));
        jobParentCategoryRepository.delete(jpc);
    }

    @Override
    public List<JobParentCategoryResponse> read() {
        List<JobParentCategoryResponse> parents = jobParentCategoryRepository.findAll().stream()
                .map(JobParentCategoryResponse::response)
                .collect(Collectors.toList());
        return parents;
    }
}
