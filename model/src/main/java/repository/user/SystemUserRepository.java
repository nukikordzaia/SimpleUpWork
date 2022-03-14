package repository.user;

import model.user.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long>, CustomSystemUserQueries {
}
