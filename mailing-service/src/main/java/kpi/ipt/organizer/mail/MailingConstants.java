package kpi.ipt.organizer.mail;

public abstract class MailingConstants {
    private MailingConstants() {
    }

    public static final String EXCHANGE_NAME = "organizer.mailing.exchange";
    public static final String QUEUE_NAME = "organizer.mailing.queue";
    public static final String ROUTING_KEY = "organizer.mailing.key";
}
