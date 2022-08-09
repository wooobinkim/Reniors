package com.common.jmark.service.bookmark;

import com.common.jmark.common.exception.DuplicateException;
import com.common.jmark.common.exception.NotFoundException;
import com.common.jmark.domain.entity.Bookmark;
import com.common.jmark.domain.entity.JobOpening;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.domain.repository.BookmarkRepository;
import com.common.jmark.domain.repository.JobOpeningRepository;
import com.common.jmark.domain.repository.user.UserRepository;
import com.common.jmark.dto.BookmarkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.jmark.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkService {
    private final UserRepository userRepository;
    private final JobOpeningRepository jobOpeningRepository;
    private final BookmarkRepository bookmarkRepository;


    @Transactional
    public Long create(Long userId, Long jobOpeningId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        JobOpening jobOpening = jobOpeningRepository.findById(jobOpeningId)
                .orElseThrow(()->new NotFoundException(JOB_OPENING_NOT_FOUND));
        bookmarkRepository.findByUser(user).forEach(bookmark -> {
            if (bookmark.getJobOpening().getId().equals(jobOpening.getId())) {
                throw new DuplicateException(String.format("%s의 해당 공고는 이미 등록된 관심 공고입니다.", jobOpening.getCompany().getName()));
            }
        });
        Bookmark bookmark = Bookmark.create(user, jobOpening);
        return bookmarkRepository.save(bookmark).getId();
    }


    @Transactional
    public List<BookmarkResponse> readList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        List<BookmarkResponse> bookmarks = bookmarkRepository.findByUser(user).stream()
                .map(BookmarkResponse::response)
                .collect(Collectors.toList());
        return bookmarks;
    }


    @Transactional
    public BookmarkResponse read(Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(()->new NotFoundException(BOOKMARK_NOT_FOUND));
        BookmarkResponse bookmarkResponse = BookmarkResponse.response(bookmark);
        return bookmarkResponse;
    }


    @Transactional
    public void delete(Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(()->new NotFoundException(BOOKMARK_NOT_FOUND));
        bookmarkRepository.delete(bookmark);
    }
}
