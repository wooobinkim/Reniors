package com.common.reniors.dto.notification;

import com.common.reniors.domain.entity.Type.IsRead;
import com.common.reniors.domain.entity.Type.JobOpeningProcess;
import com.common.reniors.domain.entity.notification.Notification;
import com.common.reniors.dto.apply.ApplyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    private Long id;

    private JobOpeningProcess jobOpeningProcess;

    private IsRead isRead;

    private LocalDateTime createdAt;

    private ApplyResponse applyResponse;

    public static NotificationResponse response(Notification notification) {
        return new NotificationResponse(notification.getId(),
                notification.getJobOpeningProcess(),
                notification.getIsRead(),
                notification.getCreatedDate(),
                ApplyResponse.response(notification.getApply()));
    }
}
