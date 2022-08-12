package com.common.reniors.dto.notification;

import com.common.reniors.domain.entity.Type.IsRead;
import com.common.reniors.domain.entity.Type.JobOpeningProcess;
import com.common.reniors.domain.entity.notification.Notification;
import com.common.reniors.dto.apply.ApplyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    private Long id;

    private JobOpeningProcess jobOpeningProcess;

    private IsRead isRead;

    private Date createdAt;

    private ApplyResponse applyResponse;

    public static NotificationResponse response(Notification notification) {
        return new NotificationResponse(notification.getId(),
                notification.getJobOpeningProcess(),
                notification.getIsRead(),
                notification.getCreatedAt(),
                ApplyResponse.response(notification.getApply()));
    }
}
