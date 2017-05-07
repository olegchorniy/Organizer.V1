package kpi.ipt.organizer.repository;

import kpi.ipt.organizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
