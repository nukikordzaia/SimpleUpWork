package com.simpleupwork.repository.user.usergroup;

import com.simpleupwork.model.user.UserGroup;
import com.simpleupwork.utils.ListResult;

public interface CustomUserGroupQueries {

	ListResult<UserGroup> filterByAttributes(String name, String permission, Boolean active, int limit, int page);
}
