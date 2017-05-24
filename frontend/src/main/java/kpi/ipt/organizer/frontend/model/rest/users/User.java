package kpi.ipt.organizer.frontend.model.rest.users;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private long id;
    private String name;
    private String email;
}
