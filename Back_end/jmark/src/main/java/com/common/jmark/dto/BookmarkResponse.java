package com.common.jmark.dto;

import com.common.jmark.domain.entity.Bookmark;
import com.common.jmark.dto.category.GugunResponse;
import com.common.jmark.dto.category.JobChildCategoryResponse;
import com.common.jmark.dto.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponse {
    private Long id;

    private UserResponse userResponse;

    private JobOpeningDto jobOpeningDto;

    public static BookmarkResponse response(Bookmark bookmark) {
        return new BookmarkResponse(bookmark.getId(),
                UserResponse.response(bookmark.getUser()),
                new JobOpeningDto(bookmark.getJobOpening(),
                        new CompanyDto(bookmark.getJobOpening().getCompany()),
                        GugunResponse.response(bookmark.getJobOpening().getGugun()),
                        JobChildCategoryResponse.response(bookmark.getJobOpening().getJobChildCategory())
                        ));
    }
}
