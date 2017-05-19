package kpi.ipt.organizer.users.repository;

import kpi.ipt.organizer.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u WHERE u.email = :email")
    long countUsersByEmail(@Param("email") String email);
}
