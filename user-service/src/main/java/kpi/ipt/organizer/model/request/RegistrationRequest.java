package kpi.ipt.organizer.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {

    private String name;
    private String email;
    private String password;
}
