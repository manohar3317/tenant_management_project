package com.notificationservice.service;

import com.notificationservice.dto.NotificationRequest;
import com.notificationservice.model.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailService emailService;
    private final SmsService smsService;

    public void send(NotificationRequest request) {
        try {
            if (request.getType() == NotificationType.EMAIL) {
                emailService.sendEmail(request);
            } else if (request.getType() == NotificationType.SMS) {
                smsService.sendSms(request);
            }
        } catch (Exception e) {
            throw new RuntimeException("Notification failed: " + e.getMessage());
        }
    }
}
