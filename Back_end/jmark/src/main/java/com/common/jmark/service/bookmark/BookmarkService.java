package com.common.jmark.service.bookmark;

import com.common.jmark.dto.BookmarkResponse;

import java.util.List;

public interface BookmarkService {
    Long create(Long userId, Long jobOpeningId);

    List<BookmarkResponse> readList(Long userId);

    BookmarkResponse read(Long bookmarkId);

    void delete(Long bookmarkId);
}
