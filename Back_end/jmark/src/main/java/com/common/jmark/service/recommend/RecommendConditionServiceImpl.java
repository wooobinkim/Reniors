package com.common.jmark.service.recommend;

import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.domain.entity.recommend.RecommendCondition;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.domain.repository.category.GugunRepository;
import com.common.jmark.domain.repository.category.JobChildCategoryRepository;
import com.common.jmark.domain.repository.recommend.RecommendConditionRepository;
import com.common.jmark.domain.repository.user.UserRepository;
import com.common.jmark.dto.recommend.RecommendConditionCreateRequest;
import com.common.jmark.dto.recommend.RecommendConditionResponse;
import com.common.jmark.dto.recommend.RecommendConditionUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.common.jmark.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendConditionServiceImpl implements RecommendConditionService {
    private final UserRepository userRepository;
    private final JobChildCategoryRepository jobChildCategoryRepository;
    private final GugunRepository gugunRepository;
    private final RecommendConditionRepository recommendConditionRepository;

    @Override
    @Transactional
    public Long create(Long userId, RecommendConditionCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(request.getJobChildCategoryId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        Gugun gugun = gugunRepository.findById(request.getGugunId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        RecommendCondition recommendCondition = RecommendCondition.create(user, jobChildCategory, gugun, request.getWorkingDay(), request.getMinSalary());
        return recommendConditionRepository.save(recommendCondition).getId();
    }

    @Override
    @Transactional
    public void update(Long recommendConditionId, RecommendConditionUpdateRequest request) {
        RecommendCondition recommendCondition = recommendConditionRepository.findById(recommendConditionId)
                .orElseThrow(()->new NotFoundException(RECOMMEND_CONDITION_NOT_FOUND));
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(request.getJobChildCategoryId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        Gugun gugun = gugunRepository.findById(request.getGugunId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        recommendCondition.update(jobChildCategory, gugun, request.getWorkingDay(), request.getMinSalary());
    }

    @Override
    @Transactional
    public void delete(Long recommendConditionId) {
        RecommendCondition recommendCondition = recommendConditionRepository.findById(recommendConditionId)
                .orElseThrow(()->new NotFoundException(RECOMMEND_CONDITION_NOT_FOUND));
        recommendConditionRepository.delete(recommendCondition);
    }

    @Override
    @Transactional
    public RecommendConditionResponse read(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        RecommendConditionResponse recommendConditionResponse = RecommendConditionResponse.response(recommendConditionRepository.findByUser(user).get());
        return recommendConditionResponse;
    }
}
