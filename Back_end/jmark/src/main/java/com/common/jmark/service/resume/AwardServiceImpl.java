package com.common.jmark.service.resume;

import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.resume.Award;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.domain.repository.resume.AwardRepository;
import com.common.jmark.domain.repository.user.UserRepository;
import com.common.jmark.dto.resume.AwardCreateRequest;
import com.common.jmark.dto.resume.AwardResponse;
import com.common.jmark.dto.resume.AwardUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.jmark.common.exception.NotFoundException.AWARD_NOT_FOUND;
import static com.common.jmark.common.exception.NotFoundException.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AwardServiceImpl implements AwardService {
    private final UserRepository userRepository;

    private final AwardRepository awardRepository;

    @Override
    @Transactional
    // 수상경력 등록 시 동일한 이름의 상을 수상한 경험이 있을 수 있기 때문에 중복 허용
    public Long create(Long userId, AwardCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        Award award = Award.create(request.getName(), request.getAwardedAt(), user);
        return awardRepository.save(award).getId();
    }

    @Override
    @Transactional
    public void update(Long awardId, AwardUpdateRequest request) {
        Award award = awardRepository.findById(awardId)
                .orElseThrow(()->new NotFoundException(AWARD_NOT_FOUND));
        award.update(request.getName(), request.getAwardedAt());
    }

    @Override
    @Transactional
    public void delete(Long awardId) {
        Award award = awardRepository.findById(awardId)
                .orElseThrow(()->new NotFoundException(AWARD_NOT_FOUND));
        awardRepository.delete(award);
    }

    @Override
    @Transactional
    public List<AwardResponse> read(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        List<AwardResponse> awards = awardRepository.findByUser(user).stream()
                .map(AwardResponse::response)
                .collect(Collectors.toList());
        return awards;
    }
}