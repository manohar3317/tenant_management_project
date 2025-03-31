package com.notificationservice.service;

import com.notificationservice.dto.NotificationRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService {

    @Value("${twilio.from.phone}")
    private String fromPhone;

    public void sendSms(NotificationRequest request) {
        Message.creator(
                new PhoneNumber(request.getTo()),
                new PhoneNumber(fromPhone),
                request.getMessage()
        ).create();
    }
}
