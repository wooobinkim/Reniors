package com.common.jmark.service.resume;

import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.resume.CareerDetail;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.domain.repository.resume.CareerDetailRepository;
import com.common.jmark.domain.repository.user.UserRepository;
import com.common.jmark.dto.resume.CareerDetailCreateRequest;
import com.common.jmark.dto.resume.CareerDetailResponse;
import com.common.jmark.dto.resume.CareerDetailUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.jmark.common.exception.NotFoundException.CAREER_DETAIL_NOT_FOUND;
import static com.common.jmark.common.exception.NotFoundException.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CareerDetailService {
    private final UserRepository userRepository;
    private final CareerDetailRepository careerDetailRepository;

    @Transactional
    public Long create(Long userId, CareerDetailCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        CareerDetail careerDetail = CareerDetail.create(request.getCompanyName(), request.getStartedAt(), request.getFinishedAt(), request.getJobContents(), user);
        return careerDetailRepository.save(careerDetail).getId();
    }

    @Transactional
    public void update(Long careerDetailId, CareerDetailUpdateRequest request) {
        CareerDetail careerDetail = careerDetailRepository.findById(careerDetailId)
                .orElseThrow(()->new NotFoundException(CAREER_DETAIL_NOT_FOUND));
        careerDetail.update(request.getCompanyName(), request.getStartedAt(), request.getFinishedAt(), request.getJobContents());
    }

    @Transactional
    public void delete(Long careerDetailId) {
        CareerDetail careerDetail = careerDetailRepository.findById(careerDetailId)
                .orElseThrow(()->new NotFoundException(CAREER_DETAIL_NOT_FOUND));
        careerDetailRepository.delete(careerDetail);
    }

    @Transactional
    public List<CareerDetailResponse> readList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        List<CareerDetailResponse> careerDetails = careerDetailRepository.findByUser(user).stream()
                .map(CareerDetailResponse::response)
                .collect(Collectors.toList());
        return careerDetails;
    }

    @Transactional
    public CareerDetailResponse read(Long careerDetailId) {
        CareerDetail careerDetail = careerDetailRepository.findById(careerDetailId)
                .orElseThrow(()->new NotFoundException(CAREER_DETAIL_NOT_FOUND));
        CareerDetailResponse careerDetailResponse = CareerDetailResponse.response(careerDetail);
        return careerDetailResponse;
    }
}
