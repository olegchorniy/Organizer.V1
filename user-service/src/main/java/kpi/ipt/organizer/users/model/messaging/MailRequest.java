package kpi.ipt.organizer.users.model.messaging;

import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MailRequest {

    private Set<String> receivers;
    private MailType mailType;
    private Map<String, Object> mailParameters;
}
