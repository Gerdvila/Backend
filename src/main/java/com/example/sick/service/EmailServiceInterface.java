package com.example.sick.service;

import com.example.sick.api.model.request.MailRequest;
import com.example.sick.api.model.response.MailResponse;
import com.example.sick.repository.MailRepository;

import java.util.List;

public interface EmailServiceInterface {
    void sendMail(String recipient, String subject, String message) throws Exception;
    void saveMailHistory(MailRequest mailRequest);
    List<MailResponse> getMailByApplicationId(long applicationId);


}