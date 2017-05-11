package kpi.ipt.organizer.mail.service;

import kpi.ipt.organizer.mail.model.MailType;

import java.io.Writer;
import java.util.Map;

public interface MailBodyRenderer {

    void render(MailType mailType, Map<String, Object> parameters, Writer out);
}
