package kpi.ipt.organizer.frontend.model.rest.users;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthRequest {

    private String email;
    private String password;
}