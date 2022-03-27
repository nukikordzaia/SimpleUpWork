package com.simpleupwork.repository.application;

import com.simpleupwork.model.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>, CustomApplicationQueries {
}
