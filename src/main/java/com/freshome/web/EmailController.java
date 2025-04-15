package com.freshome.web;

import com.freshome.service.verification.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(
            @RequestParam String toEmail,
            @RequestParam(required = false) String subject,
            @RequestParam String body) {
        emailService.sendSimpleEmail(toEmail, subject, body);
        return ResponseEntity.ok(
                "Email sent successfully"
        );
    }
}
