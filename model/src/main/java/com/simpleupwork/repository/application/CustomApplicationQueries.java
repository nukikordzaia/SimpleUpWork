package com.simpleupwork.repository.application;

import com.simpleupwork.model.application.Application;
import com.simpleupwork.utils.ListResult;

import java.util.Date;

public interface CustomApplicationQueries {

	ListResult<Application> filterByAttributes(String name, String skills, Long salary, Boolean active, Date createTime,
                                               Date startDate, Date endDate, int limit, int page);
}
