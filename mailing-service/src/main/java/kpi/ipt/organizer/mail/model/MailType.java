package kpi.ipt.organizer.mail.model;

public enum MailType {

    GREETING("Welcome on-board!", "greeting");

    MailType(String subject, String templateName) {
        this.subject = subject;
        this.templateName = templateName;
    }

    private final String subject;
    private final String templateName;

    public String getSubject() {
        return subject;
    }

    public String getTemplateName() {
        return templateName;
    }
}
