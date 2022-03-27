package com.simpleupwork.repository.user;

import com.simpleupwork.model.user.SystemUser;
import com.simpleupwork.utils.ListResult;

public interface CustomSystemUserQueries {

	ListResult<SystemUser> filterByAttributes(String firstName, String lastName, String username, String title,
                                              String email, String userGroups, String skills, Boolean active, int limit, int page);
}
