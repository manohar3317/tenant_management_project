package com.notificationservice.dto;

import com.notificationservice.model.NotificationType;
import lombok.Data;

@Data
public class NotificationRequest {
    private String to;
    private String subject; // Used for email only
    private String message;
    private NotificationType type;
}
