package com.simpleupwork.repository.user;

import com.simpleupwork.model.user.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long>, CustomSystemUserQueries {
}
