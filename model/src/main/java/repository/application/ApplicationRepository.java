package repository.application;

import model.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long>, CustomApplicationQueries {
}
