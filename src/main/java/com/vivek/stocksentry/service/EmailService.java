package com.vivek.stocksentry.service;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Value("${sendgrid.api.key}")
    private String apiKey;

    @Value("${sendgrid.sender.email}")
    private String senderEmail;

    public void sendEmail(String to,
                          String subject,
                          String body) throws IOException {

        Email from = new Email(senderEmail);
        Email recipient = new Email(to);

        Content content = new Content(
                "text/plain",
                body
        );

        Mail mail = new Mail(
                from,
                subject,
                recipient,
                content
        );

        SendGrid sendGrid = new SendGrid(apiKey);

        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        sendGrid.api(request);
    }
}