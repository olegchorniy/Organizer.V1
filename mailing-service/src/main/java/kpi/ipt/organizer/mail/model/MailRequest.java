package kpi.ipt.organizer.mail.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
public class MailRequest {

    private Set<String> receivers;
    private MailType mailType;
    private Map<String, Object> mailParameters;
}
