package kpi.ipt.organizer.frontend.model.rest.users;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthResponse {

    private boolean authenticated;
    private Long userId;
}