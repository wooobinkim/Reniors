package com.common.reniors.service.recommend;

import com.common.reniors.common.exception.DuplicateException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.category.Gugun;
import com.common.reniors.domain.entity.category.JobChildCategory;
import com.common.reniors.domain.entity.recommend.RecommendCondition;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.category.GugunRepository;
import com.common.reniors.domain.repository.category.JobChildCategoryRepository;
import com.common.reniors.domain.repository.recommend.RecommendConditionRepository;
import com.common.reniors.domain.repository.user.UserRepository;
import com.common.reniors.dto.recommend.RecommendConditionCreateRequest;
import com.common.reniors.dto.recommend.RecommendConditionResponse;
import com.common.reniors.dto.recommend.RecommendConditionUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.common.reniors.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendConditionService {
    private final UserRepository userRepository;
    private final JobChildCategoryRepository jobChildCategoryRepository;
    private final GugunRepository gugunRepository;
    private final RecommendConditionRepository recommendConditionRepository;


    @Transactional
    public Long create(Long userId, RecommendConditionCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        if (recommendConditionRepository.findByUserId(userId).isPresent()) {
            throw new DuplicateException(String.format("%s님의 추천 조건이 이미 존재합니다.", user.getName()));
        }
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(request.getJobChildCategoryId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        Gugun gugun = gugunRepository.findById(request.getGugunId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        RecommendCondition recommendCondition = RecommendCondition.create(user, jobChildCategory, gugun, request.getWorkingDay(), request.getMinSalary());
        return recommendConditionRepository.save(recommendCondition).getId();
    }


    @Transactional
    public void update(Long userId, RecommendConditionUpdateRequest request) {
        RecommendCondition recommendCondition = recommendConditionRepository.findByUserId(userId)
                .orElseThrow(()->new NotFoundException(RECOMMEND_CONDITION_NOT_FOUND));
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(request.getJobChildCategoryId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        Gugun gugun = gugunRepository.findById(request.getGugunId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        recommendCondition.update(jobChildCategory, gugun, request.getWorkingDay(), request.getMinSalary());
    }


    @Transactional
    public void delete(Long userId) {
        RecommendCondition recommendCondition = recommendConditionRepository.findByUserId(userId)
                .orElseThrow(()->new NotFoundException(RECOMMEND_CONDITION_NOT_FOUND));
        recommendConditionRepository.delete(recommendCondition);
    }


    @Transactional
    public RecommendConditionResponse read(Long userId) {
        RecommendConditionResponse recommendConditionResponse = RecommendConditionResponse.response(recommendConditionRepository.findByUserId(userId).get());
        return recommendConditionResponse;
    }
}
