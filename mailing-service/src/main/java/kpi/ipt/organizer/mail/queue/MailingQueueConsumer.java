package kpi.ipt.organizer.mail.queue;

import kpi.ipt.organizer.mail.MailingConstants;
import kpi.ipt.organizer.mail.model.MailRequest;
import kpi.ipt.organizer.mail.service.MailSender;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MailingQueueConsumer {

    private final MailSender mailSender;

    public MailingQueueConsumer(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = MailingConstants.QUEUE_NAME)
    public void handleMailSendingRequest(@Payload MailRequest mailRequest) {
        mailSender.sendEmail(mailRequest);
    }
}
