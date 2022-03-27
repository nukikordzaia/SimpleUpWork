package com.simpleupwork.repository.user.usergroup;

import com.simpleupwork.model.user.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long>, CustomUserGroupQueries {
}
