package repository.sysparam;

import model.sysparams.SystemParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysParamRepository extends JpaRepository<SystemParameter, Long>, CustomSystemParamQueries {
}
