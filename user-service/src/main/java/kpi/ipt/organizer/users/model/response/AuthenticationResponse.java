package kpi.ipt.organizer.users.model.response;

import kpi.ipt.organizer.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private boolean authenticated;
    private User user;
}