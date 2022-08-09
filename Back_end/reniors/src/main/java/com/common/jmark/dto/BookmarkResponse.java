package com.common.jmark.dto;

import com.common.jmark.domain.entity.Bookmark;
import com.common.jmark.dto.jobOpening.JobOpeningResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponse {
    private Long id;

    private JobOpeningResponse jobOpeningResponse;

    public static BookmarkResponse response(Bookmark bookmark) {
        return new BookmarkResponse(bookmark.getId(),
                JobOpeningResponse.response(bookmark.getJobOpening()));
    }
}
