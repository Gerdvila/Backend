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
