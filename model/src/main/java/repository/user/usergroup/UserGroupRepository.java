package repository.user.usergroup;

import model.user.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long>, CustomUserGroupQueries {
}
