package com.common.jmark.service.category;

import com.common.jmark.common.exception.DuplicateException;
import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.domain.entity.category.Sido;
import com.common.jmark.domain.repository.category.GugunRepository;
import com.common.jmark.domain.repository.category.SidoRepository;
import com.common.jmark.dto.category.GugunCreateRequest;
import com.common.jmark.dto.category.GugunResponse;
import com.common.jmark.dto.category.GugunUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.jmark.common.exception.NotFoundException.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GugunService{

    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;

    @Transactional
    public Long create(Long sidoId, GugunCreateRequest request) {

        Sido sido = sidoRepository.findById(sidoId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));

        if(gugunRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리 입니다.", request.getName()));
        }
        Gugun gugun = Gugun.create(request.getName(), request.getCode(), sido);
        return gugunRepository.save(gugun).getId();
    }

    @Transactional
    public void update(Long gugunId, GugunUpdateRequest request) {
        Sido sido = sidoRepository.findById(request.getSidoId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        Gugun gugun = gugunRepository.findById(gugunId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        if(gugunRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리 입니다.", request.getName()));
        }
        gugun.update(request.getName(), request.getCode(), sido);
    }

    @Transactional
    public void delete(Long gugunId) {
        Gugun gugun = gugunRepository.findById(gugunId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        gugunRepository.delete(gugun);
    }

    @Transactional
    public List<GugunResponse> getGugunList(Long sidoId) {
        Sido sido = sidoRepository.findById(sidoId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        List<GugunResponse> guguns = gugunRepository.findBySido(sido).stream()
                .map(GugunResponse::response)
                .collect(Collectors.toList());
        return guguns;
    }
}
