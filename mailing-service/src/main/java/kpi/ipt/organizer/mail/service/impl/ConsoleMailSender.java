package kpi.ipt.organizer.mail.service.impl;

import kpi.ipt.organizer.mail.model.MailRequest;
import kpi.ipt.organizer.mail.model.MailType;
import kpi.ipt.organizer.mail.service.MailBodyRenderer;
import kpi.ipt.organizer.mail.service.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.StringWriter;

@Service
public class ConsoleMailSender implements MailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleMailSender.class);

    private final MailBodyRenderer mailRenderer;

    public ConsoleMailSender(MailBodyRenderer mailRenderer) {
        this.mailRenderer = mailRenderer;
    }

    @Override
    public void sendEmail(MailRequest mailRequest) {
        MailType mailType = mailRequest.getMailType();

        StringWriter bodyWriter = new StringWriter();
        mailRenderer.render(mailType, mailRequest.getMailParameters(), bodyWriter);

        LOGGER.info("Mail has been sent: receivers={}, subject=\"{}\", body=\"{}\"",
                mailRequest.getReceivers(), mailType.getSubject(), bodyWriter.toString());
    }
}
