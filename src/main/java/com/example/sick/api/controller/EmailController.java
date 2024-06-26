package com.example.sick.api.controller;


import com.example.sick.api.model.request.MailRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.sick.service.EmailService;

@RestController
@RequestMapping("/admin")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) throws Exception {
        this.emailService = emailService;
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public String sendEmail(@PathVariable String email) {
        try {
            emailService.sendMail(email, "TLizingas Loan", """
                    Hey there!,

                    Thank you for using the TLizingas loan calculator!
                    We've successfully received your application.

                    TLizingas staff will get in touch with you shortly!

                    Have a great day!
                    TLizingas Team
                    """);
            return "Email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send email: " + e.getMessage();
        }
    }

    @PostMapping("/mail/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEmail(@RequestBody MailRequest email) throws Exception {
        emailService.saveMailHistory(email);
    }

    @GetMapping("/mail/{applicationId}")
    @ResponseStatus(HttpStatus.OK)
    public String getMailByApplicationId(@PathVariable long applicationId) {
        return emailService.getMailByApplicationId(applicationId).toString();
    }
}
