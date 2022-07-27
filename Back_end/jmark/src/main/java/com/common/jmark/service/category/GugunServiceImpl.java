package com.common.jmark.service.category;

import com.common.exception.DuplicateException;
import com.common.exception.NotFoundException;
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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GugunServiceImpl implements GugunService{

    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;

    @Override
    @Transactional
    public Long create(Long sidoId, GugunCreateRequest request) {

        Sido sido = sidoRepository.findById(sidoId)
                .orElseThrow(()->new NotFoundException("Not Found Sido"));

        if(gugunRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리 입니다.", request.getName()));
        }
        Gugun gugun = Gugun.create(request.getName(), request.getCode(), sido);
        return gugunRepository.save(gugun).getId();
    }

    @Override
    @Transactional
    public void update(Long gugunId, GugunUpdateRequest request) {
        Gugun gugun = gugunRepository.findById(gugunId)
                .orElseThrow(()->new NotFoundException("Not Found Gugun"));
        if(gugunRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리 입니다.", request.getName()));
        }
        gugun.update(request.getName(), request.getCode(), request.getSido());
    }

    @Override
    @Transactional
    public void delete(Long gugunId) {
        Gugun gugun = gugunRepository.findById(gugunId)
                .orElseThrow(()->new NotFoundException("Not Found Gugun"));
        gugunRepository.delete(gugun);
    }

    @Override
    @Transactional
    public List<GugunResponse> read(Long sidoId) {
        Sido sido = sidoRepository.findById(sidoId)
                .orElseThrow(()->new NotFoundException("Not Found Sido"));
        List<GugunResponse> guguns = gugunRepository.findBySido(sido).stream()
                .map(GugunResponse::response)
                .collect(Collectors.toList());
        return guguns;
    }
}
