package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.model.Email;

import javax.mail.MessagingException;

public interface IEmailService {
    public String sendMail(Email email) throws MessagingException;
    public String getLink(String token);
}
