package com.notificationservice.service;

import com.notificationservice.dto.NotificationRequest;
import com.sendgrid.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${sendgrid.from.email}")
    private String fromEmail;

    public void sendEmail(NotificationRequest request) throws IOException {
        Email from = new Email(fromEmail);
        Email to = new Email(request.getTo());
        Content content = new Content("text/plain", request.getMessage());
        Mail mail = new Mail(from, request.getSubject(), to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request sgRequest = new Request();
        sgRequest.setMethod(Method.POST);
        sgRequest.setEndpoint("mail/send");
        sgRequest.setBody(mail.build());

        sg.api(sgRequest);
    }
}
