package kpi.ipt.organizer.mail.service;

import kpi.ipt.organizer.mail.model.MailRequest;

public interface MailSender {

    void sendEmail(MailRequest mailRequest);
}
