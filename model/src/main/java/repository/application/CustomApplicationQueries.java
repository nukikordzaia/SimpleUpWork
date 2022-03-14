package repository.application;

import model.application.Application;
import utils.ListResult;

import java.util.Date;

public interface CustomApplicationQueries {

	ListResult<Application> filterByAttributes(String name, Long salary, Boolean active, Date createTime,
											   Date startDate, Date endDate, int limit, int page);
}
