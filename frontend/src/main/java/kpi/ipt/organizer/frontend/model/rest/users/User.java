package kpi.ipt.organizer.frontend.model.rest.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class User implements Serializable {

    private long id;
    private String name;
    private String email;
}
