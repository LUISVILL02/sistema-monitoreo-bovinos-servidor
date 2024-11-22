package org.server.apimonitoreo.service;

public interface EmailService {
    void sendEmail(String[] toUser, String subject, String message);
}
