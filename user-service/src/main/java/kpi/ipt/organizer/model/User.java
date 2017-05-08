package kpi.ipt.organizer.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(UserViews.Public.class)
    private long id;

    @JsonView(UserViews.Public.class)
    private String name;

    @JsonView(UserViews.Public.class)
    private String email;

    private String password;
}
