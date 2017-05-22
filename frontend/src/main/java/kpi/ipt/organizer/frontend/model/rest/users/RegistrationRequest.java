package kpi.ipt.organizer.frontend.model.rest.users;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    private String name;
    private String email;
    private String password;
}
