package com.common.reniors.service.recoding;


import com.common.reniors.common.exception.NotAuthException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.board.Board;
import com.common.reniors.domain.entity.category.JobParentCategory;
import com.common.reniors.domain.entity.recoding.Recoding;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.recoding.RecodingRepository;
import com.common.reniors.dto.board.BoardCreateRequest;
import com.common.reniors.dto.board.CommentResponse;
import com.common.reniors.dto.recoding.RecodingCreateRequest;
import com.common.reniors.dto.recoding.RecodingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotAuthException.USER_NO_AUTH;
import static com.common.reniors.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecodingService {

    private final RecodingRepository recodingRepository;

    @Transactional
    public Long create(RecodingCreateRequest request, String baseURL, String recodeName, User user) {
        Recoding recoding = Recoding.create(request.getOriginalName(), baseURL, recodeName, user);
        return recodingRepository.save(recoding).getId();
    }

    @Transactional
    public String delete(Long recodingId, User user) {
        Recoding recoding = recodingRepository.findById(recodingId)
                .orElseThrow(()->new NotFoundException(RECODING_NOT_FOUND));
        if(recoding.getUser().getId() == user.getId()) {
            recodingRepository.delete(recoding);
            return recoding.getRecodeName();
        }else{
            throw new NotAuthException(USER_NO_AUTH);
        }
    }

    @Transactional
    public List<RecodingResponse> getRecodingList(User user) {
        List<RecodingResponse> recodings = recodingRepository.findByUser(user).stream()
                .map(RecodingResponse::response)
                .collect(Collectors.toList());
        return recodings;
    }

}
