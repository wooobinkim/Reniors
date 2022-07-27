package com.common.jmark.service.category;

import com.common.exception.DuplicateException;
import com.common.exception.NotFoundException;
import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.domain.entity.category.JobParentCategory;
import com.common.jmark.domain.repository.category.JobChildCategoryRepository;
import com.common.jmark.domain.repository.category.JobParentCategoryRepository;
import com.common.jmark.dto.category.JobChildCategoryCreateRequest;
import com.common.jmark.dto.category.JobChildCategoryResponse;
import com.common.jmark.dto.category.JobChildCategoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobChildCategoryServiceImpl implements JobChildCategoryService{

    private final JobParentCategoryRepository jobParentCategoryRepository;
    private final JobChildCategoryRepository jobChildCategoryRepository;

    @Override
    @Transactional
    public Long create(Long jpcId, JobChildCategoryCreateRequest request) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(jpcId)
                .orElseThrow(()->new NotFoundException("Not Found Parent"));
        if(jobChildCategoryRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리입니다.",request.getName()));
        }
        JobChildCategory jcc = JobChildCategory.create(request.getName(), jpc);
        return jobChildCategoryRepository.save(jcc).getId();
    }

    @Override
    @Transactional
    public void update(Long jccId, JobChildCategoryUpdateRequest request) {
        JobChildCategory jcc = jobChildCategoryRepository.findById(jccId)
                .orElseThrow(()->new NotFoundException("Not Found Child"));
        if(jobChildCategoryRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리입니다.", request.getName()));
        }
        jcc.update(jcc.getName(), jcc.getParent());
    }

    @Override
    @Transactional
    public void delete(Long jccId) {
        JobChildCategory jcc = jobChildCategoryRepository.findById(jccId)
                .orElseThrow(()-> new NotFoundException("Not Found Child"));
        jobChildCategoryRepository.delete(jcc);
    }

    @Override
    @Transactional
    public List<JobChildCategoryResponse> read(Long jpcId) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(jpcId)
                .orElseThrow(()->new NotFoundException("Not Found Parent"));
        List<JobChildCategoryResponse> childs = jobChildCategoryRepository.findByParent(jpc).stream()
                .map(JobChildCategoryResponse::response)
                .collect(Collectors.toList());
        return null;
    }
}
