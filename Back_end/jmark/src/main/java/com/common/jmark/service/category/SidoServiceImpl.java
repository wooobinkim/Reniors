package com.common.jmark.service.category;

import com.common.exception.DuplicateException;
import com.common.exception.NotFoundException;
import com.common.jmark.domain.entity.category.Sido;
import com.common.jmark.domain.repository.category.SidoRepository;
import com.common.jmark.dto.category.SidoCreateRequest;
import com.common.jmark.dto.category.SidoResponse;
import com.common.jmark.dto.category.SidoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SidoServiceImpl implements SidoService{

    private final SidoRepository sidoRepository;

    @Override
    @Transactional
    public Long create(SidoCreateRequest request) {
        if(sidoRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리입니다.",request.getName()));
        }
        System.out.println("dddd");
        Sido sido = Sido.create(request.getName(), request.getCode());
        System.out.println("aaaaaa");
        return sidoRepository.save(sido).getId();
    }

    @Override
    @Transactional
    public void update(Long sidoId, SidoUpdateRequest request) {
        Sido sido = sidoRepository.findById(sidoId)
                .orElseThrow(()->new NotFoundException("Not Found Sido"));
        if(sidoRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리입니다.",request.getName()));
        }
        sido.update(request.getName(), request.getCode());
    }

    @Override
    @Transactional
    public void delete(Long sidoId) {
        Sido sido = sidoRepository.findById(sidoId)
                .orElseThrow(()->new NotFoundException("Not Found Sido"));
        sidoRepository.delete(sido);
    }

    @Override
    @Transactional
    public List<SidoResponse> read() {
        List<SidoResponse> sidos = sidoRepository.findAll().stream()
                .map(SidoResponse::response)
                .collect(Collectors.toList());
        return sidos;
    }
}
