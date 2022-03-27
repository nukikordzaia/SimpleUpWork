package service.application;

import com.simpleupwork.model.application.Application;
import com.simpleupwork.repository.application.ApplicationRepository;
import com.simpleupwork.utils.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ApplicationService {

	@Autowired
	ApplicationRepository applicationRepository;

	public Optional<Application> findById(Long id) {
		return applicationRepository.findById(id);
	}

	public ListResult<Application> filterByAttributes(String name, Long salary, Boolean active, Date createTime,
													  Date startDate, Date endDate, int limit, int page) {
		return applicationRepository.filterByAttributes(name, salary, active, createTime, startDate, endDate, limit, page);
	}

	public Application save(Application application) {
		return applicationRepository.save(application);
	}

	public void delete(Long id) {
		applicationRepository.deleteById(id);
	}
}
