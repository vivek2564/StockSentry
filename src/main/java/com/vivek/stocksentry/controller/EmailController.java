package com.vivek.stocksentry.controller;
import com.vivek.stocksentry.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(
            EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/test")
    public String testEmail() throws Exception {

        emailService.sendEmail(
                "kumarvivek4560@gmail.com",
                "StockSentry Test",
                "Email service working successfully"
        );

        return "Email Sent";
    }
}